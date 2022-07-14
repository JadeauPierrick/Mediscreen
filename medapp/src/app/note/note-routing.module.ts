import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NoteFormComponent } from "./note-form/note-form.component";
import { NoteListComponent } from "./note-list/note-list.component";


const routes: Routes = [
    {path: 'notes/update/:id', component: NoteFormComponent},
    {path: 'notes/add', component: NoteFormComponent},
    {path: '', component: NoteListComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class NoteRoutingModule {}