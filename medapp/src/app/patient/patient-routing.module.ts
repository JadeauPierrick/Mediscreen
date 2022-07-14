import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { PatientDetailComponent } from "./patient-detail/patient-detail.component";
import { PatientFormComponent } from "./patient-form/patient-form.component";
import { PatientListComponent } from "./patient-list/patient-list.component";

const routes: Routes = [
    {path: 'patients/update/:id', component: PatientFormComponent},
    {path: 'patients/add', component: PatientFormComponent},
    {path: 'patients', component: PatientListComponent},
    {path: 'patients/:id', component: PatientDetailComponent},
    {path: 'patients/note/:patientId', loadChildren: ()=> import('../note/note.module').then(m => m.NoteModule)},
    {path: 'patients/assessment/:patientId', loadChildren: ()=> import('../assessment/assessment.module').then(m => m.AssessmentModule)}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class PatientRoutingModule {}