<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php
$fp = fopen("compteur.txt", "r+"); 
$nb_visites = fgets ($fp, 11);
// on augmente le nombre de visites de 1.
$nb_visites = $nb_visites + 1;
// on se positionne au tout début de notre fichier.
fseek ($fp, 0);
// on écrit dans le fichier la nouvelle valeur correspondant au nombre de visites.
fputs ($fp, $nb_visites);
fclose($fp);
echo 'Ce site compte '.$nb_visites.' visiteurs !';
?>
    
</body>
</html>