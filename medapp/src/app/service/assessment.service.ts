import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Assessment } from '../interface/assessment';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  private readonly apiUrl = 'http://localhost:8080/assessment';

  constructor(private http: HttpClient) { }

  getAssessment(patientId: number): Observable<Assessment> {
    return this.http.get<Assessment>(`${this.apiUrl}/${patientId}`).pipe(
      catchError(error => this.handleError(error, null))
    );
  }

  private handleError(error: Error, errorValue: any) {
    console.log(error);
    return of(errorValue);
  }
}
