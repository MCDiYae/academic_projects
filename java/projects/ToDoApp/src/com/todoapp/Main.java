package com.todoapp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException  {
		TaskManager taskManager = new TaskManager();
		Scanner scanner = new Scanner(System.in);
		taskManager.loadTasksFromDatabase();

        while (true) {
            System.out.println("1. Ajouter une tache ");
            System.out.println("2. Afficher les taches ");
            System.out.println("3. Afficher les details d'une tache ");
            System.out.println("4. Marquer une tache comme terminee ");
            System.out.println("0. Quitter ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
				
					ajouterTache(scanner, taskManager);
				
                    break;
                case 2:
                	
                	taskManager.displayTasks();
                    break;
                case 3:
                    afficherDetailsTache(scanner, taskManager);
                    break;
                case 4:
                    marquerTacheTerminee(scanner, taskManager);
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                    System.exit(0);
                default:
                    System.out.println("Choix non valide.");
            }
        }
    }

	private static void marquerTacheTerminee(Scanner scanner, TaskManager taskManager) {
	    System.out.print("Nom de la tâche à marquer comme terminée : ");
	    String nomTache = scanner.nextLine();
	    Task tache = taskManager.getTaskByName(nomTache);

	    if (tache != null) {
	        taskManager.markTaskAsDone(tache);
	        taskManager.removeCompletedTasks();
	        System.out.println("La tâche a été marquée comme terminée.");
	    } else {
	        System.out.println("Tâche introuvable.");
	    }
	}

	private static void afficherDetailsTache(Scanner scanner, TaskManager taskManager) throws SQLException {
		taskManager.loadTasksFromDatabase();
	    System.out.print("Nom de la tâche : ");
	    String nomTache = scanner.nextLine();
	    Task tache = taskManager.getTaskByName(nomTache);

	    if (tache != null) {
	        taskManager.displayTaskDetails(tache);
	    } else {
	        System.out.println("Tâche introuvable.");
	    }
	}

	private static void ajouterTache(Scanner scanner, TaskManager taskManager) throws SQLException {
	    System.out.print("Nom de la tâche : ");
	    String nom = scanner.nextLine();

	    System.out.print("Description de la tâche : ");
	    String description = scanner.nextLine();

	    System.out.print("Date d'échéance (AAAA-MM-JJ) : ");
	    String dueDateString = scanner.nextLine();
	    LocalDate dueDate = LocalDate.parse(dueDateString);

	    System.out.print("Priorité : ");
	    int priority = scanner.nextInt();

	    System.out.print("La tâche est-elle terminée ? (true/false) : ");
	    boolean completed = scanner.nextBoolean();

	    Task nouvelleTache = new Task(nom, description, dueDate, priority, completed);
	    taskManager.addTask(nouvelleTache);
	    taskManager.saveTasksToDatabase(nouvelleTache);

	    System.out.println("Tâche ajoutée avec succès !");
	}
	
	


	}

