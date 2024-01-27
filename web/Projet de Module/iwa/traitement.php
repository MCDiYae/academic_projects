<?php
// Assurez-vous que le formulaire a été soumis
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Récupérer les données du formulaire
    $nom = htmlspecialchars($_POST['nom']);
    $prenom = htmlspecialchars($_POST['prenom']);
    $tele = htmlspecialchars($_POST['tele']);
    $dop = htmlspecialchars($_POST['dop']);
    $evp = htmlspecialchars($_POST['EVP']);
    $filiere = htmlspecialchars($_POST['filiere']);
    $a_obt = htmlspecialchars($_POST['A_obt']);
    $e_mail = htmlspecialchars($_POST['e_mail']);

    // Vous pouvez ajouter des validations supplémentaires ici si nécessaire

    // Connexion à la base de données
    $servername = "localhost"; // Remplacez par le nom de votre serveur MySQL
    $username = "votre_nom_utilisateur"; // Remplacez par votre nom d'utilisateur MySQL
    $password = "votre_mot_de_passe"; // Remplacez par votre mot de passe MySQL
    $dbname = "votre_nom_de_base_de_donnees"; // Remplacez par le nom de votre base de données MySQL

    $conn = new mysqli($servername, $username, $password, $dbname);

    // Vérifier la connexion
    if ($conn->connect_error) {
        die("La connexion à la base de données a échoué : " . $conn->connect_error);
    }

    // Préparer la requête SQL pour l'insertion
    $sql = "INSERT INTO votre_table (nom, prenom, tele, dop, evp, filiere, a_obt, e_mail) 
            VALUES ('$nom', '$prenom', '$tele', '$dop', '$evp', '$filiere', '$a_obt', '$e_mail')";

    // Exécuter la requête
    if ($conn->query($sql) === TRUE) {
        echo "Enregistrement réussi.";
    } else {
        echo "Erreur : " . $sql . "<br>" . $conn->error;
    }

    // Fermer la connexion à la base de données
    $conn->close();
}
?>
