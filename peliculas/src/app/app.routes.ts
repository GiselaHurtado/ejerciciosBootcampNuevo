
import { Routes } from '@angular/router';
import { ActorListComponent } from './components/actor-list/actor-list.component';
import { FilmListComponent } from './components/film-list/film-list.component';
import { ActorFormComponent } from './components/actor-form/actor-form.component';
import { FilmFormComponent } from './components/film-form/film-form.component';
import { PageNotFoundComponent } from '../app/main/page-not-found/page-not-found.component';
import { HomeComponent } from '../../src/app/main/home/home.component';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';
import { LanguageListComponent } from './components/language-list/language-list.component';
import { LanguageFormComponent } from './components/language-form/language-form.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'actor', component: ActorListComponent },
  { path: 'actor/new', component: ActorFormComponent },
  { path: 'actor/edit/:id', component: ActorFormComponent },
  { path: 'film', component: FilmListComponent },
  { path: 'film/new', component: FilmFormComponent },
  { path: 'film/edit/:id', component: FilmFormComponent },
  { path: 'category', component: CategoryListComponent },
  { path: 'category/new', component: CategoryFormComponent },
  { path: 'category/edit/:id', component: CategoryFormComponent },
  { path: 'language', component: LanguageListComponent },
  { path: 'language/new', component: LanguageFormComponent },
  { path: 'language/edit/:id', component: LanguageFormComponent },
  { path: '**', component: PageNotFoundComponent }
];
