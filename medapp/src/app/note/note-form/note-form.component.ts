import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, UrlSegment } from '@angular/router';
import { StatusFormEnum } from 'src/app/enum/status-form.enum';
import { Note } from 'src/app/interface/note';
import { NoteService } from 'src/app/service/note.service';

@Component({
  selector: 'app-note-form',
  templateUrl: './note-form.component.html',
  styleUrls: ['./note-form.component.css']
})
export class NoteFormComponent implements OnInit {

  note: Note;
  noteForm: FormGroup;
  statusForm: StatusFormEnum;
  statusFormEnum = StatusFormEnum;
  
  constructor(
    private formBuilder: FormBuilder,
    private noteService: NoteService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.url.subscribe((urls: UrlSegment[]) => {
      this.statusForm = this.setStatusForm(urls);
      if(this.statusForm !== StatusFormEnum.CREATE) {
        this.noteService.getNoteById(+urls[urls.length - 1].path)
        .subscribe((note: Note) => {
          this.note = note;
          this.initNoteForm();
        });
      } else {
        this.initNoteForm();
      }
    });
  }

  private initNoteForm(): void {
    this.noteForm = this.formBuilder.group({
      id: [this.note && this.note.id ? this.note.id : null],
      patientId: [this.note && this.note.patientId ? this.note.patientId : null, 
        [Validators.required]],  
      createdAt: [this.note && this.note.createdAt ? this.note.createdAt : null],
      updatedAt: [this.note && this.note.updatedAt ? this.note.updatedAt : null],
      content: [this.note && this.note.content ? this.note.content : null, 
        [Validators.required]],
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
    const form = {...this.noteForm.value} as Note;
    if(this.statusForm === StatusFormEnum.CREATE) {
      this.noteService.add(form).subscribe((note: Note) => this.router.navigate(['/patients/note', note.patientId]));
    } else if(this.statusForm === StatusFormEnum.UPDATE){
      this.noteService.update(this.note.id, form).subscribe((note: Note) => this.router.navigate(['/patients/note', note.patientId]));  
    }
  }

  cancel() {
    if(this.statusForm === StatusFormEnum.CREATE) {
      this.router.navigate(['/patients']);
    } else {
      this.router.navigate(['/patients/note',this.note.patientId]);
    }
  }

}
