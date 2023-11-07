function addColor() {
    // Cette fonction est appelée lorsque le bouton "Ajouter à la file d'attente" est cliqué.

    // Récupère la valeur entrée dans l'élément d'entrée de texte avec l'ID "colorInput".
    var color = document.getElementById('colorInput').value;

    // Récupère tous les éléments HTML avec la classe "rectangle" et les stocke dans un tableau.
    var boxes = document.querySelectorAll('.rectangle');

    // Cette boucle parcourt les boîtes du tableau, en commençant par la dernière et en allant vers la première.
    for (var i = boxes.length - 1; i > 0; i--) {
        // Affecte la couleur de la boîte précédente à la boîte actuelle.
        boxes[i].style.backgroundColor = boxes[i - 1].style.backgroundColor;
    }

    // Affecte la couleur entrée dans la première boîte du tableau.
    boxes[0].style.backgroundColor = color;
}