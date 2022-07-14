import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Note } from '../interface/note';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private readonly apiUrl = 'http://localhost:8082/notes';

  constructor(private http: HttpClient) { }

  getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}`).pipe(
      catchError(error => this.handleError(error, []))
    );
  }

  getNoteById(noteId: number): Observable<Note> {
    return this.http.get<Note>(`${this.apiUrl}/${noteId}`).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  getAllNotesByPatientId(patientId: number): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}/patient/${patientId}`).pipe(
      catchError(error => this.handleError(error, []))
    );
  }

  add(note: Note): Observable<Note> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.post<Note>(`${this.apiUrl}/add`, note, httpOptions).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  update(noteId: number, note: Note): Observable<Note> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.put<Note>(`${this.apiUrl}/update/${noteId}`, note, httpOptions).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  deleteById(noteId: number): Observable<Note> {
    return this.http.delete<Note>(`${this.apiUrl}/delete/${noteId}`).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  private handleError(error: Error, errorValue: any) {
    console.log(error);
    return of(errorValue);
  }
}
