import { Component, OnInit } from '@angular/core';
import { DataState } from 'src/app/enum/data-state.enum';
import { Patient } from 'src/app/interface/patient';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[];
  dataState: DataState = DataState.LOADING_STATE;
  dataStateEnum = DataState;

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.patientService.getPatients().subscribe((patientList: Patient[]) => this.patients = patientList);
    this.dataState = DataState.LOADED_STATE;
  }

  deletePatient(patient: Patient): void {
    this.patientService.deleteById(patient.id).subscribe(() => this.ngOnInit());
  }

}
