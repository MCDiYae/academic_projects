<?php
$con = mysqli_connect("localhost","root","","testdb");

if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    exit();
}

$tableCreationQuery = "CREATE TABLE IF NOT EXISTS liste_proprietaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    telephone VARCHAR(15) NOT NULL
)";

mysqli_query($con, $tableCreationQuery) or die('Erreur lors de la création de la table : ' . mysqli_error($con));

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Récupérer les données du formulaire
    $nom = mysqli_real_escape_string($con, $_POST['nom']);
    $telephone = mysqli_real_escape_string($con, $_POST['telephone']);

    // Insérer les données dans la base de données
    $insertQuery = "INSERT INTO liste_proprietaire (nom, telephone) VALUES ('$nom', '$telephone')";
    mysqli_query($con, $insertQuery) or die('Erreur lors de l\'insertion des données : ' . mysqli_error($con));
}

?>
<html>
<body>
<?php
// lancement de la requête (on n’impose aucune condition puisque l'on désire obtenir la liste complète des propriétaires
$sql = 'SELECT telephone, nom FROM liste_proprietaire';
// on lance la requête (mysqli_query) et on impose un message d'erreur si la requête ne se passe pas bien (or die)
$req = mysqli_query($con, $sql) or die('Erreur SQL !<br />'.$sql.'<br />'.mysqli_error($con));
// on va scanner tous les enregistrements un par un
while ($data = mysqli_fetch_array($req)) {
 // on affiche les résultats
 echo 'Nom : '.$data['nom'].'<br />';
 echo 'Son tél : '.$data['telephone'].'<br /><br />';
}
mysqli_free_result ($req);
mysqli_close ($con);
?>
<!-- Ajout du formulaire pour ajouter de nouveaux enregistrements -->
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
    <label for="nom">Nom :</label>
    <input type="text" name="nom" required><br>

    <label for="telephone">Téléphone :</label>
    <input type="text" name="telephone" required><br>

    <input type="submit" value="Ajouter">
</form>
</body>
</html>