import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  etudiants=[
    {"id":1,"nom":"MANSOURI","age":23},
    {"id":2,"nom":"HOUSSNI","age":22},
    {"id":3,"nom":"BAKKALI","age":24},
    ]

  constructor() { }
  getEtudiants(){
    return this.etudiants;
  }
  addEtudiant(etudiant:any){
    this.etudiants.push(etudiant)
  }
}
