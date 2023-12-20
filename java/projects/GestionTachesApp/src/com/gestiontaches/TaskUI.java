package com.gestiontaches;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;


public class TaskUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TaskManager taskManager;
    private JTable taskTable;
	protected TaskTableModel tableModel;
	

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
		setTitle("Gestion de mes taches");
		taskManager = new TaskManager();
		taskTable = new JTable(tableModel);
		try {
			taskManager.establishConnection();
			taskManager.loadTasksFromDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton addButton = new JButton("Ajouter Tâche");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddTaskDialog();
			}
		});
		
		JButton removeButton = new JButton("Supprimer");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RemoveTaskDialog();
            }
        });
        
        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUpdateTaskDialog();
            }
        });
        
        JButton markDoneButton = new JButton("Tâche terminée");
        markDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    showMarkDoneDialog(selectedRow);
                } else {
                    // Aucune ligne sélectionnée, affichez un message à l'utilisateur
                    JOptionPane.showMessageDialog(TaskUI.this, "Veuillez sélectionner une tâche à marquer comme terminée.");
                }
            }
        });

        

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(5, 311, 574, 33);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(markDoneButton);

		tableModel = new TaskTableModel(taskManager.getTasks());
		taskTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(taskTable);
		scrollPane.setBounds(5, 11, 574, 289);
		contentPane.setLayout(null);

		contentPane.add(buttonPanel);
		contentPane.add(scrollPane);

		setContentPane(contentPane);
		
		
	}

	private void RemoveTaskDialog() {
	    int selectedRow = taskTable.getSelectedRow();

	    if (selectedRow != -1) {
	        int option = JOptionPane.showConfirmDialog(
	                this,
	                "Voulez-vous vraiment supprimer cette tâche?",
	                "Confirmation de suppression",
	                JOptionPane.YES_NO_OPTION);

	        if (option == JOptionPane.YES_OPTION) {
	            String selectedTaskName = (String) taskTable.getValueAt(selectedRow, 0);
	            Task taskToRemove = findTaskByName(selectedTaskName);

	            if (taskToRemove != null) {
	                try {
	                    taskManager.removeTask(taskToRemove);
	                    taskManager.displayTasks();
	                    
	                    // Rafraîchir la table
                        tableModel.setData(taskManager.getTasks());
                        tableModel.fireTableDataChanged();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de la tâche.");
	                }
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Veuillez sélectionner une tâche à supprimer.");
	    }
	    this.setVisible(true);
	}

	private void showAddTaskDialog() {
	    JFrame frame = new JFrame("Ajouter une Tâche");
	    frame.setSize(300, 200);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel panel = new JPanel(new GridLayout(5, 2));

	    JLabel nameLabel = new JLabel("Nom:");
	    JTextField nameField = new JTextField();

	    JLabel descriptionLabel = new JLabel("Description:");
	    JTextField descriptionField = new JTextField();

	    JLabel dueDateLabel = new JLabel("Date d'échéance (AAAA-MM-JJ):");
	    JTextField dueDateField = new JTextField();

	    JLabel priorityLabel = new JLabel("Priorité:");
	    JTextField priorityField = new JTextField();

	    JButton addButton = new JButton("Ajouter");
	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            try {
	                String name = nameField.getText();
	                String description = descriptionField.getText();
	                LocalDate dueDate = LocalDate.parse(dueDateField.getText());
	                int priority = Integer.parseInt(priorityField.getText());

	                Task newTask = new Task(name, description, dueDate, priority, false);

	                // Ajoutez la tâche à la liste et à la base de données
	                taskManager.addTask(newTask);
	                taskManager.saveTasksToDatabase();

	                               
                 // Rafraîchir la table
                    
                    tableModel.setData(taskManager.getTasks());
                    tableModel.fireTableDataChanged();
             
	                
	                frame.dispose();
	            } catch (Exception ex) {
	               
	                ex.printStackTrace();

	                // Affichez un message d'erreur à l'utilisateur
	                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de la tâche.");
	            }
	        }
	    });
	    
	    


	    panel.add(nameLabel);
	    panel.add(nameField);
	    panel.add(descriptionLabel);
	    panel.add(descriptionField);
	    panel.add(dueDateLabel);
	    panel.add(dueDateField);
	    panel.add(priorityLabel);
	    panel.add(priorityField);
	    panel.add(addButton);
	    

	    frame.getContentPane().add(panel);
	    frame.setVisible(true);
	}

	private void showUpdateTaskDialog() {
	    int selectedRow = taskTable.getSelectedRow();

	    if (selectedRow != -1) {
	        String selectedTaskName = (String) taskTable.getValueAt(selectedRow, 0);  // Assurez-vous que la première colonne est celle du nom
	        Task selectedTask = findTaskByName(selectedTaskName);

	        if (selectedTask != null) {
	            JFrame updateFrame = new JFrame("Mettre à jour la Tâche");
	            updateFrame.setSize(300, 200);
	            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	            JPanel panel = new JPanel(new GridLayout(5, 2));

	            JLabel nameLabel = new JLabel("Nom:");
	            JTextField nameField = new JTextField(selectedTask.getName());
	            nameField.setEditable(false);  // Le nom ne peut pas être modifié

	            JLabel descriptionLabel = new JLabel("Description:");
	            JTextField descriptionField = new JTextField(selectedTask.getDescription());

	            JLabel dueDateLabel = new JLabel("Date d'échéance (AAAA-MM-JJ):");
	            JTextField dueDateField = new JTextField(selectedTask.getDueDate().toString());

	            JLabel priorityLabel = new JLabel("Priorité:");
	            JTextField priorityField = new JTextField(String.valueOf(selectedTask.getPriority()));

	            JButton updateButton = new JButton("Mettre à jour");
	            updateButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    try {
	                        // Mise à jour des propriétés de la tâche
	                        selectedTask.setDescription(descriptionField.getText());
	                        selectedTask.setDueDate(LocalDate.parse(dueDateField.getText()));
	                        selectedTask.setPriority(Integer.parseInt(priorityField.getText()));

	                        // Mettre à jour la tâche dans la base de données
	                        taskManager.updateTask(selectedTask);
	                       
	                        // Rafraîchir la table
	                        tableModel.setData(taskManager.getTasks());
	                        tableModel.fireTableDataChanged();
	                        
	                        updateFrame.dispose();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                        // Affichez un message d'erreur à l'utilisateur
	                        JOptionPane.showMessageDialog(updateFrame, "Erreur lors de la mise à jour de la tâche.");
	                    }
	                }
	                
	            });
	            
	            

	            panel.add(nameLabel);
	            panel.add(nameField);
	            panel.add(descriptionLabel);
	            panel.add(descriptionField);
	            panel.add(dueDateLabel);
	            panel.add(dueDateField);
	            panel.add(priorityLabel);
	            panel.add(priorityField);
	            panel.add(updateButton);
	            

	            updateFrame.getContentPane().add(panel);
	            updateFrame.setVisible(true);
	        }
	       
	    } 
	}

	private void showMarkDoneDialog(int selectedRow) {
	    int option = JOptionPane.showConfirmDialog(this, "Avez-vous terminé cette tâche ?", "Confirmation", JOptionPane.YES_NO_OPTION);

	    if (option == JOptionPane.YES_OPTION) {
	        try {
	            // Récupérer la tâche sélectionnée
	            Task selectedTask = taskManager.getTasks().get(selectedRow);

	            // Marquer la tâche comme terminée
	            taskManager.markTaskAsDone(selectedTask);
	            
	            // Rafraîchir la table
                tableModel.setData(taskManager.getTasks());
                tableModel.fireTableDataChanged();
	           
                // Afficher un message de confirmation
	            JOptionPane.showMessageDialog(this, "La tâche a été marquée comme terminée.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de l'état de la tâche.");
	        }
	    }
	    
	}

	
	private Task findTaskByName(String taskName) {
	    for (Task task : taskManager.getTasks()) {
	        if (task.getName().equals(taskName)) {
	            return task;
	        }
	    }
	    return null;  
	}
}