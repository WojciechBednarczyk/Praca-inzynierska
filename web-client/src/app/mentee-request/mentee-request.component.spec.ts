import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MenteeRequestComponent} from './mentee-request.component';

describe('MenteeRequestComponent', () => {
  let component: MenteeRequestComponent;
  let fixture: ComponentFixture<MenteeRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenteeRequestComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(MenteeRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
