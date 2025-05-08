import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TelaInsercaoComponent } from './components/tela-insercao/tela-insercao.component';
import { TelaMontagemComponent } from './components/tela-montagem/tela-montagem.component';
import { TelaConsultaComponent } from './components/tela-consulta/tela-consulta.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IntegranteService } from './services/integrante.service';
import { HttpClientModule } from '@angular/common/http';
import { TimeService } from './services/time-service.service';
import { ApiService } from './services/api-service.service';

@NgModule({
  declarations: [
    AppComponent,
    TelaInsercaoComponent,
    TelaMontagemComponent,
    TelaConsultaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule    
  ],
  providers: [IntegranteService, TimeService, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
