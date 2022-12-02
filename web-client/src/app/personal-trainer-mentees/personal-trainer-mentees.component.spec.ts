import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PersonalTrainerMenteesComponent} from './personal-trainer-mentees.component';

describe('PersonalTrainerMenteesComponent', () => {
  let component: PersonalTrainerMenteesComponent;
  let fixture: ComponentFixture<PersonalTrainerMenteesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonalTrainerMenteesComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PersonalTrainerMenteesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
