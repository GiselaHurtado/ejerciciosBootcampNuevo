import { TestBed } from '@angular/core/testing';

import { ServiciosService } from '../contactos/servicios.service';

describe('ServiciosService', () => {
  let service: ServiciosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiciosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
