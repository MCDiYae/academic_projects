import { Component, ViewChild } from '@angular/core';
import { ChildComponent } from "./child/child.component";
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-parent',
    standalone: true,
    templateUrl: './parent.component.html',
    styleUrl: './parent.component.css',
    imports: [ChildComponent,FormsModule]
})
export class ParentComponent {

@ViewChild("idChild") child!:ChildComponent
parentValue="p"

fromChild() {
    this.parentValue=this.child.childValue
console.log("from child"+ this.child.childValue)
}
toChild() {
    this.child.childValue=this.parentValue
console.log("to child"+this.parentValue)
}
}
