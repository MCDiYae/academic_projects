package com.appgestion.model;

import javax.swing.*;

import com.appgestion.database.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    public void establishConnection() throws SQLException {
        connection = DatabaseConnector.getConnection();
        System.out.println("Connection Established successfully");
    }

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

    
    public void addTask(Task newTask) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("La connexion à la base de données n'est pas établie.");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO tasks (name, description, dueDate, priority, completed) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, newTask.getName());
            preparedStatement.setString(2, newTask.getDescription());
            preparedStatement.setDate(3, Date.valueOf(newTask.getDueDate()));
            preparedStatement.setInt(4, newTask.getPriority());
            preparedStatement.setBoolean(5, newTask.isCompleted());

            preparedStatement.executeUpdate();
        }

        // Mettez à jour la liste des tâches en mémoire
        tasks.add(newTask);

        // Mettez à jour le modèle de table et informez la JTable
        taskTableModel.setData(tasks);
        taskTableModel.fireTableDataChanged();
    }

    // Reste du code...
}