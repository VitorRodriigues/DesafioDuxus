export class IntegranteModel {
    id: number;
    franquia: string;
    nome: string;
    funcao: string;
  
    // Construtor
    constructor(id: number, franquia: string, nome: string, funcao: string) {
      this.id = id;
      this.franquia = franquia;
      this.nome = nome;
      this.funcao = funcao;
    }
  }