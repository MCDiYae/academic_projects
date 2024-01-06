import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
sendData() {
throw new Error('Method not implemented.');
}
  formData=this.fb.group({
    firstName:[],
    lastName:[],
    age:[],
  })
constructor(private fb:FormBuilder){

}
}
