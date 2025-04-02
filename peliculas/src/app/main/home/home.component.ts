// import { Component } from '@angular/core';
// import { ActorListComponent } from "../../components/actor-list/actor-list.component";
// import { FilmListComponent } from 'src/app/components/film-list/film-list.component';

// @Component({
//   selector: 'app-home',
//   imports: [ActorListComponent, FilmListComponent],
//   templateUrl: './home.component.html',
//   styleUrl: './home.component.css'
// })
// export class HomeComponent {
//   title: string = 'Catálogo de Películas';

// }

import { Component } from '@angular/core';
import { HeaderComponent } from '../../main/header/header.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet],
  template: `
    <app-header></app-header>
    <main class="main container">
      <h1>Catálogo de Películas</h1>
      <router-outlet></router-outlet>
    </main>
  `,
  styles: [`
    .main { padding: 1rem; }
  `]
})
export class HomeComponent { }
