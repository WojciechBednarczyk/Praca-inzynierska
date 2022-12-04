import {TestBed} from '@angular/core/testing';

import {MenteeRequestService} from './mentee-request.service';

describe('MenteeRequestService', () => {
  let service: MenteeRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenteeRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
