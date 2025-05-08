import { Component, OnInit } from '@angular/core';
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
  timeDaData: any;
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
    // Exemplo: inicializar a lista de times (você pode substituir isso conforme necessário)
    this.todosOsTimes = [];  // Aqui você pode preencher com seus times reais
  }

  consultarTimeDaData(): void {
    this.apiService.getTimeDaData(this.dataInicial)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.timeDaData = data;
        } else {
          alert("Nenhum time encontrado para a data informada.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar o time.");
      });
  }
  
  consultarIntegranteMaisUsado(): void {
    this.apiService.getIntegranteMaisUsado(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.integranteMaisUsado = data;
        } else {
          alert("Nenhum integrante encontrado no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar o integrante mais usado.");
      });
  }
  
  consultarIntegrantesDoTimeMaisComum(): void {
    this.apiService.getIntegrantesDoTimeMaisComum(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && data.length > 0) {
          this.integrantesMaisComum = data;
        } else {
          alert("Nenhum integrante encontrado no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar os integrantes do time mais comum.");
      });
  }
  
  consultarFuncaoMaisComum(): void {
    this.apiService.getFuncaoMaisComum(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.funcaoMaisComum = data;
        } else {
          alert("Nenhuma função encontrada no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar a função mais comum.");
      });
  }
  
  consultarFranquiaMaisFamosa(): void {
    this.apiService.getFranquiaMaisFamosa(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.franquiaMaisFamosa = data;
        } else {
          alert("Nenhuma franquia encontrada no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar a franquia mais famosa.");
      });
  }
  
  consultarContagemPorFranquia(): void {
    this.apiService.getContagemPorFranquia(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.contagemPorFranquia = data;
        } else {
          alert("Nenhuma contagem encontrada no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar a contagem por franquia.");
      });
  }
  
  consultarContagemPorFuncao(): void {
    this.apiService.getContagemPorFuncao(this.dataInicial, this.dataFinal)
      .subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.contagemPorFuncao = data;
        } else {
          alert("Nenhuma contagem encontrada no período informado.");
        }
      }, error => {
        console.error(error);
        alert("Erro ao consultar a contagem por função.");
      });
  }
}