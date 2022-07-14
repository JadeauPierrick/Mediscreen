import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataState } from 'src/app/enum/data-state.enum';
import { Note } from 'src/app/interface/note';
import { Patient } from 'src/app/interface/patient';
import { NoteService } from 'src/app/service/note.service';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  notes: Note[];

  patient: Patient;
  patientId: string|null = null;
  dataState: DataState = DataState.LOADING_STATE;
  dataStateEnum = DataState;

  constructor(
    private noteService: NoteService,
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.paramMap.get('patientId');
    this.fetchData();
    this.dataState = DataState.LOADED_STATE;
}

  deleteNote(note: Note): void {
    this.noteService.deleteById(note.id).subscribe(() => {
      this.fetchData();
    });
  }

  fetchData() {
    if(this.patientId) {
      this.noteService.getAllNotesByPatientId(+this.patientId)
      .subscribe((noteList: Note[]) => this.notes = noteList);
      this.patientService.getOnePatient(+this.patientId)
      .subscribe((patient: Patient) => this.patient = patient);
  }
}

  comeBackToPatientList() {
    this.router.navigate(['/patients']);
  }

}
