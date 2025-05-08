import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TimeModel } from '../model/time.model';

@Injectable({
  providedIn: 'root'
})
export class TimeService {

  private apiUrl = 'http://localhost:8080/api/times'; 

  constructor(private http: HttpClient) { }

  
  saveTime(time: TimeModel): Observable<any> {
    return this.http.post(this.apiUrl, time); 
  }
}