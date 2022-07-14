import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Patient } from '../interface/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private readonly apiUrl = 'http://localhost:8081/patients';

  constructor(private http: HttpClient) { }

  getPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.apiUrl}`).pipe(
      catchError(error => this.handleError(error, []))
    );
  }

  getOnePatient(patientId: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.apiUrl}/${patientId}`).pipe(
      catchError(error => this.handleError(error, undefined))
    );
  }

  getAllByLastName(lastName: string): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.apiUrl}/family/${lastName}`).pipe(
      catchError(error => this.handleError(error, []))
    );
  }

  add(patient: Patient): Observable<Patient> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.post<Patient>(`${this.apiUrl}/add`, patient, httpOptions).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  update(patientId: number, patient: Patient): Observable<Patient> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.put<Patient>(`${this.apiUrl}/update/${patientId}`, patient, httpOptions).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  deleteById(patientId: number): Observable<Patient> {
    return this.http.delete<Patient>(`${this.apiUrl}/delete/${patientId}`).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  private handleError(error: Error, errorValue: any) {
    console.log(error);
    return of(errorValue);
  }

}
