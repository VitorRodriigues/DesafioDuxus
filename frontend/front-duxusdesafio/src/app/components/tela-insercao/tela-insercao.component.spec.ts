import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelaInsercaoComponent } from './tela-insercao.component';

describe('TelaInsercaoComponent', () => {
  let component: TelaInsercaoComponent;
  let fixture: ComponentFixture<TelaInsercaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TelaInsercaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TelaInsercaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
