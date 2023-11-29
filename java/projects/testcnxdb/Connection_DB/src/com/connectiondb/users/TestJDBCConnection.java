package com.connectiondb.users;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBCConnection {

    public static void main(String[] args) {
        // Informations de connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/test";
        String utilisateur = "root";
        String motDePasse = "";

        // Essayer d'établir une connexion
        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");
            // Vous pouvez effectuer d'autres opérations sur la base de données ici
            insererUtilisateur(connexion, "ghbalou", "Diae", "gb.diae@example.com");
            // N'oubliez pas de fermer la connexion une fois que vous avez terminé
            connexion.close();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void insererUtilisateur(Connection connexion, String nom, String prenom, String email) {
        try {
            // Requête SQL pour insérer un utilisateur
            String query = "INSERT INTO utilisateur (nom, prenom, email) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
                // Paramètres pour la requête préparée
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, email);

                // Exécution de la requête
                int lignesAffectees = preparedStatement.executeUpdate();

                if (lignesAffectees > 0) {
                    System.out.println("Utilisateur inséré avec succès !");
                } else {
                    System.err.println("Échec de l'insertion de l'utilisateur.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

