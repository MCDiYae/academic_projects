import { Component } from '@angular/core';
import { EtudiantService } from '../etudiant.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
  })
  export class ListComponent {
  etudiants:any
  constructor(private etudiantService: EtudiantService){}
  ngOnInit() {
  this.etudiants=this.etudiantService.getEtudiants()
  }
  }