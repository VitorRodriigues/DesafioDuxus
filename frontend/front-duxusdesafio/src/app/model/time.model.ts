import { Data } from "@angular/router";
import { IntegranteModel } from "./integrante.model";

export class TimeModel {
  id!: number;                   
  data!: Data;                    
  integrantes: IntegranteModel[] = [];      
}