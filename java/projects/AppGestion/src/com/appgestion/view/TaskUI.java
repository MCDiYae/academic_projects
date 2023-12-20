package com.appgestion.view;
import com.appgestion.model.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class TaskUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TaskManager taskManager;
	private JTable taskTable;
	protected TaskTableModel taskTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskUI frame = new TaskUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskUI() {
		setTitle("Gestion de mes tâches");
        taskManager = new TaskManager();
        
        taskTableModel = new TaskTableModel(taskManager.getTasks());
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		 JButton addButton = new JButton("Ajouter Tâche");
	        addButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	TaskDialogs.showAddTaskDialog(taskManager);
	            }
	        });
	        
	        
	     // Add a button to update a task
	        JButton updateButton = new JButton("Mettre à jour");
	        updateButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 TaskDialogs.showUpdateTaskDialog(taskManager);
	            }
	        });
	     // Add a button to mark a task as completed
	        JButton markDoneButton = new JButton("Tâche terminée");
	        markDoneButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = taskTable.getSelectedRow();
	                if (selectedRow != -1) {
	                	TaskDialogs.showMarkDoneDialog(taskManager, selectedRow);
	                } else {
	                    JOptionPane.showMessageDialog(TaskUI.this, "Veuillez sélectionner une tâche à marquer comme terminée.");
	                }
	            }
	        });
	        // Add a button to remove a task
	        JButton removeButton = new JButton("Supprimer");
	        removeButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	TaskDialogs.showRemoveTaskDialog(taskManager);
	            }
	        });
	        

	        // Add the buttons to a panel
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setBounds(5, 311, 574, 33);
	        buttonPanel.add(addButton);
	        buttonPanel.add(removeButton);
	        buttonPanel.add(updateButton);
	        buttonPanel.add(markDoneButton);

	        // Add the button panel to the content pane
	        contentPane.add(buttonPanel);
	}

	
	private void refreshUI() {
        try {
            taskManager.loadTasksFromDatabase();
            taskTableModel.setData(taskManager.getTasks());
            taskTableModel.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs liées au chargement des tâches depuis la base de données
        }
    }
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
					taskManager.loadTasksFromDatabase();
		            
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
	
}
