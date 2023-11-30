<?php
// Connexion à la base de données
$host = 'localhost';
$db = 'testphpdb';
$user = 'root';
$pass = '';

try {
    $conn = new PDO("mysql:host=$host;dbname=$db", $user, $pass);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Erreur de connexion à la base de données: " . $e->getMessage();
}

// Récupération des données du formulaire
$nom = $_POST['nom'];
$prenom = $_POST['prenom'];
$email = $_POST['email'];

// Enregistrement dans la base de données
$stmt = $conn->prepare("INSERT INTO utilisateur (nom, prenom, email) VALUES (:nom, :prenom, :email)");
$stmt->bindParam(':nom', $nom);
$stmt->bindParam(':prenom', $prenom);
$stmt->bindParam(':email', $email);

$stmt->execute();

// Enregistrement dans le fichier stoc.doc
$fichier = fopen('stoc.doc', 'r');
fwrite($fichier, "Nom: $nom, Prénom: $prenom, Email: $email\n");
fclose($fichier);

echo "Enregistrement réussi !";

// Fermer la connexion à la base de données
$conn = null;
?>
