import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PatientListComponent } from "./patient-list/patient-list.component";
import { PatientRoutingModule } from "./patient-routing.module";
import { PatientDetailComponent } from './patient-detail/patient-detail.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PatientFormComponent } from './patient-form/patient-form.component';

@NgModule({
    declarations:[PatientListComponent, PatientDetailComponent, PatientFormComponent],
    imports:[
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        PatientRoutingModule
    ]
})

export class PatientModule {}