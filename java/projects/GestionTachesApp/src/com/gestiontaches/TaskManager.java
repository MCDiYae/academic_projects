package com.gestiontaches;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

public class TaskManager {
	  private List<Task> tasks;
	  private Connection connection;
	  
	  public TaskManager() {
	        this.tasks = new ArrayList<>();
	    }
	  
	  // Method pour etablir la connexion a la base de donnees
	    public void establishConnection() throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/gestiontache";
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
	        if (connection == null || connection.isClosed()) {
	            throw new SQLException("La connexion à la base de données n'est pas établie.");
	        }

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
	    public void loadTasksFromDatabase() {
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
	    
	    public void updateTask(Task existingTask, Task newTask) {
	        if (existingTask != null && newTask != null) {
	            // Mise à jour de la tâche en mémoire
	            existingTask.setName(newTask.getName());
	            existingTask.setDescription(newTask.getDescription());
	            existingTask.setDueDate(newTask.getDueDate());
	            existingTask.setPriority(newTask.getPriority());
	            existingTask.setCompleted(newTask.isCompleted());

	            // Mise à jour de la tâche dans la base de données
	            //saveTasksToDatabase();
	        }
	    }
	    
	    public void removeCompletedTasks() {
	        // Supprimer toutes les tâches terminées de la liste en mémoire
	        tasks.removeIf(Task::isCompleted);

	        // Supprimer toutes les tâches terminées de la base de données
	        if (connection != null) {
	            try (Statement statement = connection.createStatement()) {
	                statement.executeUpdate("DELETE FROM tasks WHERE completed = true");
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Gérer les erreurs lors de la suppression depuis la base de données
	            }
	        }
	    }


	    public void addTask(Task task) {
	        tasks.add(task);
	    }

	    public void removeTask(Task task) {
	        tasks.remove(task);
	    }

	    public void displayTasks() {
	        for (Task task : tasks) {
	            System.out.println(task.getName());
	        }
	    }
	    public void displayTaskDetails(Task task) {
	        System.out.println("Task Details:");
	        System.out.println("Name: " + task.getName());
	        System.out.println("Description: " + task.getDescription());
	        System.out.println("Due Date: " + task.getDueDate());
	        System.out.println("Priority: " + task.getPriority());
	        System.out.println("Completed: " + task.isCompleted());
	    }
	    
	    public void markTaskAsDone(Task task) {
	        task.setCompleted(true);
	    }

	    public Task getTaskByName(String nomTache) {
	        for (Task task : tasks) {
	            if (task.getName().equals(nomTache)) {
	                return task;
	            }
	        }
	        return null; 
	    }
	    
	

	  

}
