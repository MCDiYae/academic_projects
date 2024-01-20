import { Component } from '@angular/core';
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-etudiant',
  standalone: true,
  imports: [ListComponent],
  templateUrl: './etudiant.component.html',
  styleUrl: './etudiant.component.css'
})
export class EtudiantComponent {
  constructor(private modal:NgbModal){}
openModal(){
this.modal.open(FormComponent)
}

}
