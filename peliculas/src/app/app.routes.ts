// // src/app/app.routes.ts
// import { Routes } from '@angular/router';
// import { ActorListComponent } from './components/actor-list/actor-list.component';
// import { ActorFormComponent } from '../app/components/actor-form/actor-form.component';
// import { PageNotFoundComponent } from '../app/main/page-not-found/page-not-found.component';
// import { FilmListComponent } from './components/film-list/film-list.component';
// import { FilmFormComponent } from './components/film-form/film-form.component';

// export const routes: Routes = [
//   { path: '', component: ActorListComponent, pathMatch: 'full' },
//   // Para agregar actor
//   { path: 'actor/new', component: ActorFormComponent },
//   // Para editar actor (se pasa el id como parámetro)
//   { path: 'actor/edit/:id', component: ActorFormComponent },
//   { path: 'film', component: FilmListComponent },
//   { path: 'film/new', component: FilmFormComponent },
//   { path: 'film/edit/:id', component: FilmFormComponent },
//   { path: '404.html', component: PageNotFoundComponent },
//   { path: '**', component: PageNotFoundComponent },
// ];
import { Routes } from '@angular/router';
import { ActorListComponent } from './components/actor-list/actor-list.component';
import { FilmListComponent } from './components/film-list/film-list.component';
import { ActorFormComponent } from './components/actor-form/actor-form.component';
import { FilmFormComponent } from './components/film-form/film-form.component';
import { PageNotFoundComponent } from '../app/main/page-not-found/page-not-found.component';
import { HomeComponent } from '../../src/app/main/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'actor', component: ActorListComponent },
  { path: 'actor/new', component: ActorFormComponent },
  { path: 'actor/edit/:id', component: ActorFormComponent },
  { path: 'film', component: FilmListComponent },
  { path: 'film/new', component: FilmFormComponent },
  { path: 'film/edit/:id', component: FilmFormComponent },
  { path: '**', component: PageNotFoundComponent }
];
