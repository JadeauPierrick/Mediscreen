import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AssessmentDetailComponent } from "./assessment-detail/assessment-detail.component";
import { AssessmentRoutingModule } from "./assessment-routing.module";

@NgModule({
    declarations:[AssessmentDetailComponent],
    imports:[
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        AssessmentRoutingModule
    ]
})

export class AssessmentModule {}