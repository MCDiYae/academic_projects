function addStudent() {
    //1 er etape : recuperer les element dapres formulaire
    students = document.getElementById('students')
    td = [document.getElementById('id').value,
    document.getElementById('nom').value,
    document.getElementById('prenom').value,
    document.getElementById('filiere').value,
        "<img src=delete.jpg onclick=deleteStudent(event)>"
    ]
    
}
    ligne = document.createElement('tr')
    td.forEach((e) => {
        data = document.createElement('td')
        data.innerHTML = e
        ligne.appendChild(data)
    })
    students.appendChild(ligne)

    //add au localstorage
    student =  { "id":td[0], "nom":td[1], "prenom":td[2], "filiere":td[3] };
    localStorage.setItem(td[0], JSON.stringify(student))

    

function deleteStudent(event) {
    console.log(event.target.parentNode.parentNode)
    ligne = event.target.parentNode.parentNode
    ligne.remove()

    // Supprimer l'étudiant du localStorage
    let id = ligne.cells[0].innerHTML;
    localStorage.removeItem(id);
}
