import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NoteListComponent } from "./note-list/note-list.component";
import { NoteRoutingModule } from "./note-routing.module";
import { NoteFormComponent } from './note-form/note-form.component';

@NgModule({
    declarations:[NoteListComponent, NoteFormComponent],
    imports:[
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NoteRoutingModule
    ]
})

export class NoteModule {}