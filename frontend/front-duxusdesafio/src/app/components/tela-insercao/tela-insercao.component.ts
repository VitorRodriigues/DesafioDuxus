import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IntegranteService } from 'src/app/services/integrante.service.spec';

@Component({
  selector: 'app-tela-insercao',
  templateUrl: './tela-insercao.component.html',
  styleUrls: ['./tela-insercao.component.scss']
})
export class TelaInsercaoComponent implements OnInit {
  integranteForm: FormGroup;
  
  constructor(private fb: FormBuilder, private integranteService: IntegranteService) {
    this.integranteForm = this.fb.group({
      franquia: ['', Validators.required],
      nome: ['', [Validators.required, Validators.minLength(3)]],
      funcao: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.integranteForm.valid) {
      const newIntegrante = this.integranteForm.value;
      this.integranteService.salvarIntegrante(newIntegrante).subscribe(
        (response) => {
          console.log('Integrante salvo com sucesso!', response);
          this.integranteForm.reset(); 
        },
        (error) => {
          console.error('Erro ao salvar integrante', error);
        });
    } else {
      console.log('Formulário inválido');
    }
  }
}