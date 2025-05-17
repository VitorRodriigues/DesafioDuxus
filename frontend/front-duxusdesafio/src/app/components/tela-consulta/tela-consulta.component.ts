import { Component, OnInit } from '@angular/core';
import { TimeDaDataDto } from 'src/app/dto/TimeDaDataDto';
import { IntegranteModel } from 'src/app/model/integrante.model';
import { TimeModel } from 'src/app/model/time.model';
import { ApiService } from 'src/app/services/api-service.service';

@Component({
  selector: 'app-tela-consulta.consulta',
  templateUrl: './tela-consulta.component.html',
  styleUrls: ['./tela-consulta.component.scss']
})
export class TelaConsultaComponent implements OnInit {

  todosOsTimes: TimeModel[] = [];  // Lista de todos os times
  timeDaData!: TimeDaDataDto;
  integranteMaisUsado!: IntegranteModel;
  integrantesMaisComum: string[] = [];
  funcaoMaisComum: string = '';
  franquiaMaisFamosa: string = '';
  contagemPorFranquia: Map<string, number> = new Map();
  contagemPorFuncao: Map<string, number> = new Map();

  dataInicial: string = '';
  dataFinal: string = '';

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    // Inicialize a lista de times conforme necessário
    this.todosOsTimes = [];
  }

  private showError(error: any, defaultMsg: string): void {
    console.error(error);
    const msg = error?.error?.message || error?.message || defaultMsg;
    alert(msg);
  }

  consultarTimeDaData(): void {
    this.apiService.getTimeDaData(this.dataInicial)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.timeDaData = data;
        } else {
          alert("Nenhum time encontrado para a data informada.");
        }
      }, error => this.showError(error, "Erro ao consultar o time."));
  }

  consultarIntegranteMaisUsado(): void {
    this.apiService.getIntegranteMaisUsado(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.integranteMaisUsado = data;
        } else {
          alert("Nenhum integrante encontrado no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar o integrante mais usado."));
  }

  consultarIntegrantesDoTimeMaisComum(): void {
    this.apiService.getIntegrantesDoTimeMaisComum(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && data.length > 0) {
          this.integrantesMaisComum = data;
        } else {
          alert("Nenhum integrante encontrado no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar os integrantes do time mais comum."));
  }

  consultarFuncaoMaisComum(): void {
    this.apiService.getFuncaoMaisComum(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data != null) {
          this.funcaoMaisComum = data;
        } else {
          alert("Nenhuma função encontrada no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar a função mais comum."));
  }

  consultarFranquiaMaisFamosa(): void {
    this.apiService.getFranquiaMaisFamosa(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data != null) {
          this.franquiaMaisFamosa = data;
        } else {
          alert("Nenhuma franquia encontrada no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar a franquia mais famosa."));
  }

  consultarContagemPorFranquia(): void {
    this.apiService.getContagemPorFranquia(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.contagemPorFranquia = data;
        } else {
          alert("Nenhuma contagem encontrada no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar a contagem por franquia."));
  }

  consultarContagemPorFuncao(): void {
    this.apiService.getContagemPorFuncao(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data != null) {
          this.contagemPorFuncao = data;
        } else {
          alert("Nenhuma contagem encontrada no período informado.");
        }
      }, error => this.showError(error, "Erro ao consultar a contagem por função."));
  }
}