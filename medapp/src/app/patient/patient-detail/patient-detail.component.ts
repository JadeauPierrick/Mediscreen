import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/interface/patient';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {

  patient: Patient;

  constructor(
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    const patientId: string|null = this.route.snapshot.paramMap.get('id');
    if(patientId) {
      this.patientService.getOnePatient(+patientId)
      .subscribe((patient: Patient) => this.patient = patient);
    }
  }

  comeBackToPatientList() {
    this.router.navigate(['/patients']);
  }

}
