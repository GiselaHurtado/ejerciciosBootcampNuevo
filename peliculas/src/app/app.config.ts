import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';  
import { routes } from './app.routes';
import { ActorService } from './actores/actor.service';
import { FilmService } from './services/film.service';
import { LanguageService } from './services/language.service';
import { CategoryService } from './services/category.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    ActorService,
    FilmService,
    LanguageService,
    CategoryService,

  ]
};