import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IntegranteModel } from '../model/integrante.model';

@Injectable({
  providedIn: 'root'
})
export class IntegranteService {
  private apiUrl = 'http://localhost:8080/api/integrantes';

  constructor(private http: HttpClient) {}

  salvarIntegrante(integrante: IntegranteModel): Observable<IntegranteModel> {
    return this.http.post<IntegranteModel>(this.apiUrl, integrante);
  }

  getAllIntegrantes(): Observable<IntegranteModel[]> {
    return this.http.get<IntegranteModel[]>(this.apiUrl); 
  }

  
}