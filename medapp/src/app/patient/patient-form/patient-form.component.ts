import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, UrlSegment } from '@angular/router';
import { StatusFormEnum } from 'src/app/enum/status-form.enum';
import { Patient } from 'src/app/interface/patient';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {

  patient: Patient;
  patientForm: FormGroup;
  statusForm: StatusFormEnum;
  statusFormEnum = StatusFormEnum;

  constructor(
    private formBuilder: FormBuilder,
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.route.url.subscribe((urls: UrlSegment[]) => {
      this.statusForm = this.setStatusForm(urls);
      if(this.statusForm !== StatusFormEnum.CREATE) {
        this.patientService.getOnePatient(+urls[urls.length - 1].path)
        .subscribe((patient: Patient) => {
          this.patient = patient;
          this.initPatientForm();
        });
      } else {
        this.initPatientForm();
      }
    });
  }

  private initPatientForm(): void {
    this.patientForm = this.formBuilder.group({
      id: [this.patient && this.patient.id ? this.patient.id : null],
      lastName: [this.patient && this.patient.lastName ? this.patient.lastName : null,
        [Validators.required]],
      firstName: [this.patient && this.patient.firstName ? this.patient.firstName : null, 
        [Validators.required]],
      birthdate: [this.patient && this.patient.birthdate ? this.patient.birthdate : null, 
        [Validators.required]],
      sex: [this.patient && this.patient.sex ? this.patient.sex : null, 
        [Validators.required]],
      address: [this.patient && this.patient.address ? this.patient.address : null],
      phone: [this.patient && this.patient.phone ? this.patient.phone : null],
    });
  }

  private setStatusForm(urls: UrlSegment[]): StatusFormEnum {
    if(urls.some((url: UrlSegment)=> url.path.includes('add'))) {
      return StatusFormEnum.CREATE;
    } else {
      return StatusFormEnum.UPDATE;
    }
  }

  onSubmit() {
    const form = {...this.patientForm.value} as Patient;
    if(this.statusForm === StatusFormEnum.CREATE) {
      this.patientService.add(form).subscribe((patient: Patient) => this.router.navigate([patient.id]));
    } else if(this.statusForm === StatusFormEnum.UPDATE){
      this.patientService.update(this.patient.id, form).subscribe((patient: Patient) => this.router.navigate(['/patients', patient.id]));  
    }
  }

  cancel() {
    if(this.statusForm === StatusFormEnum.CREATE) {
      this.router.navigate(['/patients']);
    } else {
      this.router.navigate(['/patients',this.patient.id])
    }
  }
    
}
