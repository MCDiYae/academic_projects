package com.gestiontaches;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import java.sql.*;
import java.time.LocalDate;

public class TaskManager {
	  private List<Task> tasks;
	  private Connection connection;
	  private JTable taskTable;
	  private TaskTableModel taskTableModel;
	  
	  public TaskManager() {
	        this.tasks = new ArrayList<>();
	        this.taskTable = new JTable();
	        this.taskTableModel = new TaskTableModel(tasks);
	    }
	  
	  public List<Task> getTasks() {
	        return tasks;
	    }
	  public void setTaskTable(JTable taskTable) {
	        this.taskTable = taskTable;
	    }
	  // Method pour etablir la connexion a la base de donnees
	    public void establishConnection() throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/app_gestion ";
	        String username = "root";
	        String password = "";
	        try
	        {
	        connection = DriverManager.getConnection(url, username, password);
	        
	        System.out.println("Connection Established successfully");
	        
	        } catch (SQLException e) {
	        	 System.err.println("Erreur de connexion : " + e.getMessage());
	             e.printStackTrace();
	            
	        }
	    }
	    
	 // Méthode pour sauvegarder la liste des tâches dans la base de données
	    public void saveTasksToDatabase() throws SQLException {
	    	establishConnection();

	        try (Statement statement = connection.createStatement()) {
	            // Créer la table s'il n'existe pas
	            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tasks (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY," +
	                    "name VARCHAR(255) NOT NULL," +
	                    "description VARCHAR(255)," +
	                    "dueDate DATE," +
	                    "priority INT," +
	                    "completed BOOLEAN)");

	            // Supprimer toutes les tâches existantes
	            statement.executeUpdate("DELETE FROM tasks");

	            // Insérer les nouvelles tâches
	            for (Task task : tasks) {
	                String insertQuery = "INSERT INTO tasks (name, description, dueDate, priority, completed) " +
	                        "VALUES (?, ?, ?, ?, ?)";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	                    preparedStatement.setString(1, task.getName());
	                    preparedStatement.setString(2, task.getDescription());
	                    preparedStatement.setDate(3, Date.valueOf(task.getDueDate()));
	                    preparedStatement.setInt(4, task.getPriority());
	                    preparedStatement.setBoolean(5, task.isCompleted());

	                    preparedStatement.executeUpdate();
	                }
	            }
	        }
	    }
	    
	 // Methode pour charger la liste des taches depuis la base de donnees
	    public void loadTasksFromDatabase() throws SQLException {
	    	establishConnection();
		    if (connection == null) {
		        System.err.println("La connexion à la base de données n'est pas établie.");
		        return;
		    }

	        tasks.clear();

	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks")) {

	            while (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String description = resultSet.getString("description");
	                LocalDate dueDate = resultSet.getDate("dueDate").toLocalDate();
	                int priority = resultSet.getInt("priority");
	                boolean completed = resultSet.getBoolean("completed");

	                Task task = new Task(name, description, dueDate, priority, completed);
	                tasks.add(task);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }
	    }
	    
	   public void updateTask(Task updatedTask) throws SQLException {
    if (connection == null || connection.isClosed()) {
        throw new SQLException("La connexion à la base de données n'est pas établie.");
    }

    try (PreparedStatement preparedStatement = connection.prepareStatement(
            "UPDATE tasks SET description=?, dueDate=?, priority=?, completed=? WHERE name=?")) {

        preparedStatement.setString(1, updatedTask.getDescription());
        preparedStatement.setDate(2, Date.valueOf(updatedTask.getDueDate()));
        preparedStatement.setInt(3, updatedTask.getPriority());
        preparedStatement.setBoolean(4, updatedTask.isCompleted());
        preparedStatement.setString(5, updatedTask.getName());

        preparedStatement.executeUpdate();
    }
    
}

	    
	    

	    public void addTask(Task task) {
	        tasks.add(task);
	     // Mettez à jour le modèle de table et informez la JTable
	        taskTableModel.setData(tasks);
	        taskTableModel.fireTableDataChanged();
	    }

	    public void removeTask(Task task) throws SQLException {
	        if (connection == null || connection.isClosed()) {
	            throw new SQLException("La connexion à la base de données n'est pas établie.");
	        }

	        try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "DELETE FROM tasks WHERE name=?")) {

	            preparedStatement.setString(1, task.getName());

	            preparedStatement.executeUpdate();
	        }
	        displayTasks(); 
	    }

	    public void displayTasks() throws SQLException {
	    	loadTasksFromDatabase();
	    	TaskTableModel tableModel = new TaskTableModel(tasks);

	    	tableModel.setData(getTasks());
	    	tableModel.fireTableDataChanged();
	    	
	    }
	   
	
	    public void markTaskAsDone(Task task) throws SQLException {
	        task.setCompleted(true);
	        updateTask(task);
	        saveTasksToDatabase();  
	        }

	    
	    
	

	  

}
