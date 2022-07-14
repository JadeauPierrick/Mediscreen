import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Assessment } from 'src/app/interface/assessment';
import { AssessmentService } from 'src/app/service/assessment.service';

@Component({
  selector: 'app-assessment-detail',
  templateUrl: './assessment-detail.component.html',
  styleUrls: ['./assessment-detail.component.css']
})
export class AssessmentDetailComponent implements OnInit {

  assessment: Assessment;

  constructor(
    private assessmentService: AssessmentService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    const patientId: string|null = this.route.snapshot.paramMap.get('patientId');
    if(patientId) {
      this.assessmentService.getAssessment(+patientId)
      .subscribe((result: Assessment) => {
        console.log(result);
        this.assessment = result});
    }
  }

  comeBackToPatientList() {
    this.router.navigate(['/patients']);
  }


}
