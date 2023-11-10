function addStudent(){
    students=document.getElementById('students')
    td=[document.getElementById('id').value,
        document.getElementById('nom').value,
        document.getElementById('prenom').value,
        document.getElementById('filiere').value,
        "<img src=delete.jpg onclick=deleteStudent(event)>"
]
    ligne=document.createElement('tr')
    td.forEach((e)=>{
        td=document.createElement('td')
        td.innerHTML=e
        ligne.appendChild(td)
    })
    students.appendChild(ligne)
}

function deleteStudent(event){
console.log(event.target.parentNode.parentNode)
ligne=event.target.parentNode.parentNode
ligne.remove()
}