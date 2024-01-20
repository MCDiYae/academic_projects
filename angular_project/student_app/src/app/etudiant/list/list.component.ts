import { Component, ViewChild } from '@angular/core';
import { EtudiantService } from '../etudiant.service';
import { CommonModule } from '@angular/common';
import { LoupeComponent } from './loupe/loupe.component';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule,LoupeComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
  })
  export class ListComponent {
    etudiants:any
    @ViewChild("nomLoupe") loupeComponent!: LoupeComponent;
    constructor(private etudiantService: EtudiantService){}
    ngAfterViewInit() {
    const nom=this.loupeComponent.value
    if ((nom!="")&&(nom!=undefined)){
    this.etudiants=this.etudiantService.filterEtudiantsByNom(nom)
    }else{
    this.etudiants=this.etudiantService.getEtudiants()
    }
    }
  }