import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IntegranteModel } from '../model/integrante.model';
import { TimeModel } from '../model/time.model';
import { TimeDaDataDto } from '../dto/TimeDaDataDto';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080/api'; // URL do backend Spring Boot

  constructor(private http: HttpClient) { }

  getTimeDaData(data: string): Observable<TimeDaDataDto> {
    const params = new HttpParams()
      .set('data', data); // Enviar lista de times como string
    return this.http.get<TimeDaDataDto>(`${this.baseUrl}/time-da-data`, { params });
  }

  getIntegranteMaisUsado(dataInicial: string, dataFinal: string): Observable<IntegranteModel> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal); // Enviar lista de times como string
    return this.http.get<IntegranteModel>(`${this.baseUrl}/integrante-mais-usado`, { params });
  }

  getIntegrantesDoTimeMaisComum(dataInicial: string, dataFinal: string ): Observable<string[]> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal); // Enviar lista de times como string
    return this.http.get<string[]>(`${this.baseUrl}/integrantes-do-time-mais-comum`, { params });
  }
 
  getFuncaoMaisComum(dataInicial: string, dataFinal: string): Observable<string> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal);

    return this.http.get(`${this.baseUrl}/funcao-mais-comum`, {
      params,
      responseType: 'text' // informa que a resposta é texto simples
    });
  }

  getFranquiaMaisFamosa(dataInicial: string, dataFinal: string): Observable<string> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal);

    return this.http.get(`${this.baseUrl}/franquia-mais-famosa`, {
      params,
      responseType: 'text'
    });
  }

  getContagemPorFranquia(dataInicial: string, dataFinal: string ): Observable<Map<string, number>> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal); // Enviar lista de times como string
    return this.http.get<Map<string, number>>(`${this.baseUrl}/contagem-por-franquia`, { params });
  }

  getContagemPorFuncao(dataInicial: string, dataFinal: string ): Observable<Map<string, number>> {
    const params = new HttpParams()
      .set('dataInicial', dataInicial)
      .set('dataFinal', dataFinal); // Enviar lista de times como string
    return this.http.get<Map<string, number>>(`${this.baseUrl}/contagem-por-funcao`, { params });
  }
}