package com.appgestion.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.*;

import com.appgestion.model.Task;
import com.appgestion.model.TaskManager;
import com.appgestion.model.TaskTableModel;

public class TaskDialogs {
	public static void showAddTaskDialog(TaskManager taskManager) {
        // Créer la boîte de dialogue
        JDialog addTaskDialog = new JDialog();
        addTaskDialog.setTitle("Ajouter une tâche");
        addTaskDialog.setSize(400, 200);
        addTaskDialog.setLayout(new GridLayout(6, 2));

        // Ajouter des composants à la boîte de dialogue
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dueDateField = new JTextField();
        JTextField priorityField = new JTextField();

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les valeurs des champs
                String name = nameField.getText();
                String description = descriptionField.getText();
                // Convertir la date de chaîne à LocalDate (vous devrez peut-être ajuster cela)
                LocalDate dueDate = LocalDate.parse(dueDateField.getText());
                int priority = Integer.parseInt(priorityField.getText());

                Task newTask = new Task(name, description, dueDate, priority, false);
				try {
					taskManager.addTask(newTask);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				addTaskDialog.dispose();
            }
        });

        addTaskDialog.add(new JLabel("Nom:"));
        addTaskDialog.add(nameField);
        addTaskDialog.add(new JLabel("Description:"));
        addTaskDialog.add(descriptionField);
        addTaskDialog.add(new JLabel("Date d'échéance "));
        addTaskDialog.add(dueDateField);
        addTaskDialog.add(new JLabel("Priorité:"));
        addTaskDialog.add(priorityField);
        addTaskDialog.add(new JLabel("")); // Espace vide
        addTaskDialog.add(addButton);

        // Afficher la boîte de dialogue
        addTaskDialog.setVisible(true);
    }

	
	    public static void showRemoveTaskDialog(TaskManager taskManager) {
	        // Implementer la logique pour montrer la boîte de dialogue de suppression de tâche
	        // Utiliser taskManager pour supprimer la tâche de la base de données, etc.
	    }

	    public static void showUpdateTaskDialog(TaskManager taskManager) {
	        // Implementer la logique pour montrer la boîte de dialogue de mise à jour de tâche
	        // Utiliser taskManager pour mettre à jour la tâche dans la base de données, etc.
	    }

	    public static void showMarkDoneDialog(TaskManager taskManager, int selectedRow) {
	        // Implementer la logique pour montrer la boîte de dialogue de marquage de tâche comme terminée
	        // Utiliser taskManager pour marquer la tâche comme terminée dans la base de données, etc.
	    }
}
