import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { ERROR_LEVEL, LoggerService } from '@my/core';
import { environment } from 'src/environments/environment';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ajaxWaitInterceptor } from './main';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';


export const appConfig: ApplicationConfig = {
  providers: [
    LoggerService,
    { provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL },
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes, withComponentInputBinding()),
    provideHttpClient(withInterceptors([ ajaxWaitInterceptor ])),
    provideAnimationsAsync(),
    providePrimeNG({ theme: { preset: 'saga-blue' } })
  ]
};