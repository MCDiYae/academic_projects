<?php
// Récupérer les données du formulaire
$nom = $_POST['nom'];
$prenom = $_POST['prenom'];
$telephone = $_POST['Telephone'];
$diplome = $_POST['diplome'];
$etablissement = $_POST['Etablissement, ville et pays'];
$filiere = $_POST['Filière:'];
$annee_obtention = $_POST['Année d\'obtention'];
$email = $_POST['E-mail'];

// Traitement des fichiers uploadés
$cv_nom = $_FILES['cv']['name'];
$releve_nom = $_FILES['Relevé']['name'];
$releve1_nom = $_FILES['Relevé1']['name'];

// Connexion à la base de données
$servername = "localhost"; 
$database  = "u265005980_iwa";
$username = "u265005980_iwa"; 
$password = "DIae_2001"; 


$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die("La connexion à la base de données a échoué : " . $conn->connect_error);
}

// Requête SQL pour insérer les données dans la table
$sql = "INSERT INTO votre_table (nom, prenom, telephone, diplome, etablissement, filiere, annee_obtention, email, cv, releve, releve1) 
        VALUES ('$nom', '$prenom', '$telephone', '$diplome', '$etablissement', '$filiere', '$annee_obtention', '$email', '$cv_nom', '$releve_nom', '$releve1_nom')";

if ($conn->query($sql) === TRUE) {
    echo "Enregistrement réussi.";
} else {
    echo "Erreur : " . $sql . "<br>" . $conn->error;
}

// Fermer la connexion à la base de données
$conn->close();
?>
