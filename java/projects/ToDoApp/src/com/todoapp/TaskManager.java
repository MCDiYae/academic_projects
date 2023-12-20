package com.todoapp;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;







public class TaskManager {
	 
	  private List<Task> tasks;
	  private Connection connection;
	  
	  public TaskManager() {
	        this.tasks = new ArrayList<>();
	    }
	  
	  public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	// Method pour etablir la connexion a la base de donnees
	  public void establishConnection() {
	        String url = "jdbc:mysql://localhost:3306/todo";
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
	   
	// Methode pour sauvegarder la liste des taches dans la base de donnees
	  public void saveTasksToDatabase (Task task) throws SQLException {
		  establishConnection();
		  
		  try (PreparedStatement preparedStatement = connection.prepareStatement(
		            "INSERT INTO tasks (name, description, dueDate, priority, completed) VALUES (?, ?, ?, ?, ?)")) {

		        preparedStatement.setString(1, task.getName());
		        preparedStatement.setString(2, task.getDescription());
		        preparedStatement.setDate(3, Date.valueOf(task.getDueDate()));
		        preparedStatement.setInt(4, task.getPriority());
		        preparedStatement.setBoolean(5, task.isCompleted());

		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        
		        e.printStackTrace();
		    }
	  } 
	  
	// Methode pour charger la liste des taches depuis la base de donnees
	  public void loadTasksFromDatabase() {
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
	        if (tasks.isEmpty()) {
	            System.out.println("Aucune tâche disponible.");
	        } else {
	            System.out.println("Liste des tâches :");
	            for (Task task : tasks) {
	                System.out.println(task.getName());
	            }
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
	        try {
				saveTasksToDatabase(task);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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