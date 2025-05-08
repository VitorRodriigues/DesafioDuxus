import { Component, OnInit } from '@angular/core';
import { IntegranteModel } from 'src/app/model/integrante.model';
import { TimeModel } from 'src/app/model/time.model';
import { IntegranteService } from 'src/app/services/integrante.service.spec';
import { TimeService } from 'src/app/services/time-service.service';

@Component({
  selector: 'app-tela-montagem',
  templateUrl: './tela-montagem.component.html',
  styleUrls: ['./tela-montagem.component.scss']
})
export class TelaMontagemComponent implements OnInit {

  integrantes: IntegranteModel[] = [];  
  time: TimeModel = new TimeModel();         

  constructor(private integranteService: IntegranteService, private timeService: TimeService) { }

  ngOnInit(): void {
   
    this.integranteService.getAllIntegrantes().subscribe(
      data => {
        this.integrantes = data;  
      },
      error => {
        console.error('Erro ao carregar os integrantes', error);  
      }
    );
  }

    adicionarAoTime(integrante: IntegranteModel): void {
    if (!this.time?.integrantes.includes(integrante)) {  
      this.time?.integrantes.push(integrante);  
    }
  }
  
  removerDoTime(jogador: IntegranteModel): void {
    const index = this.time?.integrantes.indexOf(jogador);  
    if (index > -1) {
      this.time?.integrantes.splice(index, 1); 
    }
  }

  salvarTime(): void {
    this.timeService.saveTime(this.time).subscribe(
      response => {
        this.time = new TimeModel();
        alert('Time salvo com sucesso!');  
        console.log(response);  
      },
      error => {
        alert('Erro ao salvar o time.');  
        console.error(error);  
      }
    );
  }
}