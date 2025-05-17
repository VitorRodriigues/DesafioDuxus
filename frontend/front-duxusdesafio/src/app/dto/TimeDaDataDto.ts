export class TimeDaDataDto {
  id!: number;
  data!: string;
  integrantes!: string[];

  constructor(id: number, data: string, integrantes: string[]) {
    this.id = id;
    this.data = data;
    this.integrantes = integrantes;
  }
}