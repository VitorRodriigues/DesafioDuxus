import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelaMontagemComponent } from './tela-montagem.component';

describe('TelaMontagemComponent', () => {
  let component: TelaMontagemComponent;
  let fixture: ComponentFixture<TelaMontagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TelaMontagemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TelaMontagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
