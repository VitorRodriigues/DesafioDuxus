import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TelaInsercaoComponent } from './components/tela-insercao/tela-insercao.component';
import { TelaMontagemComponent } from './components/tela-montagem/tela-montagem.component';
import { TelaConsultaComponent } from './components/tela-consulta/tela-consulta.component';

const routes: Routes = [
  { path: '', redirectTo: 'tela-insercao', pathMatch: 'full' },
  { path: 'tela-insercao', component: TelaInsercaoComponent },
  { path: 'tela-montagem', component: TelaMontagemComponent },
  { path: 'tela-consulta', component: TelaConsultaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}