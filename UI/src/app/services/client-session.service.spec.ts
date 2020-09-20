import { TestBed } from '@angular/core/testing';

import { ClientSessionService } from './client-session.service';

describe('ClientSessionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClientSessionService = TestBed.get(ClientSessionService);
    expect(service).toBeTruthy();
  });
});
