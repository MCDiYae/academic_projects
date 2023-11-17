function ajouter() {
    nom=document.getElementById("nom").value
    age=document.getElementById("age").value
    person={"nom":nom,"age":age}
    localStorage.setItem(nom,JSON.stringify(person))
    sessionStorage.setItem(nom,person)
}

function afficher(){
   
    nom=document.getElementById("nom").value
    person=localStorage.getItem(nom)
    person=JSON.parse(person)
    if (person!=null)
    document.getElementById("age").value=person.age
}

function supprimer(){
    nom=document.getElementById("nom").value
    localStorage.removeItem(nom)
}