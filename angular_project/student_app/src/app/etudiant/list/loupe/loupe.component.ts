import { Component, EventEmitter, Output } from '@angular/core';
import { ListComponent } from '../list.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-loupe',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './loupe.component.html',
  styleUrl: './loupe.component.css'
})
export class LoupeComponent {
  opacity:string
  value!:string
  @Output() childEvent = new EventEmitter();
  constructor(){
    this.opacity="0"
  }
  afficherInput(){
  this.opacity="1"
  }
  masquerInput(){
  if ((this.value==undefined)||(this.value==""))
  this.opacity="0"
  }
  
  filterData(){
      //méthode qui envoie this.valeur vers le composant parent listComponent
      this.childEvent.emit(this.value)
  }        
}
