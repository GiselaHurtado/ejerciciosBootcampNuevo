// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { ActorListComponent } from './components/actor-list/actor-list.component';
import { ActorFormComponent } from '../app/components/actor-form/actor-form.component';
import { PageNotFoundComponent } from '../app/main/page-not-found/page-not-found.component';

export const routes: Routes = [
  { path: '', component: ActorListComponent, pathMatch: 'full' },
  // Para agregar actor
  { path: 'actor/new', component: ActorFormComponent },
  // Para editar actor (se pasa el id como parámetro)
  { path: 'actor/edit/:id', component: ActorFormComponent },
  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];
