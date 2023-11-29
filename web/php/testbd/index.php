<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'inscription</title>
</head>
<body>
    <h1>Formulaire d'inscription</h1>
    <form action="traitement.php" method="post">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" required><br>

        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" required><br>

        <label for="email">Email :</label>
        <input type="email" name="email" required><br>

        <input type="submit" value="Envoyer">
    </form>
</body>
</html>
