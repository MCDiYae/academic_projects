import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  backEndURL="http://localhost:8080/students"
  etudiants:any

  constructor(private http:HttpClient) { }
  getEtudiants(){
    this.http.get(this.backEndURL).subscribe(data=>{
    this.etudiants=data
    })
    return this.etudiants
   }

  addEtudiant(etudiant:any){
    this.etudiants.push(etudiant)
  }
  filterEtudiantsByNom(nom:string){
    if ((nom!="")&&(nom!=undefined)){
    return this.etudiants.filter((
    etudiant: { nom: string;}
    )=>etudiant.nom.toLowerCase().startsWith(nom.toLowerCase())
    )
    }
    return this.etudiants
    }
}
