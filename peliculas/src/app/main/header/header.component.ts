// import { Component } from '@angular/core';
// import { RouterLink, RouterLinkActive } from '@angular/router';

// @Component({
//   selector: 'app-header',
//   imports: [RouterLink, RouterLinkActive],
//   templateUrl: '../header/header.component.html',
//   styleUrl: './header.component.css'
// })
// export class HeaderComponent {

// }

import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <header>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbarDark" aria-controls="offcanvasNavbarDark" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="offcanvas offcanvas-end text-bg-light" tabindex="-1" id="offcanvasNavbarDark"
            aria-labelledby="offcanvasNavbarDarkLabel">
            <div class="offcanvas-header">
              <h5 class="offcanvas-title" id="offcanvasNavbarDarkLabel">Menu</h5>
              <button type="button" class="btn-close btn-close-black" data-bs-dismiss="offcanvas"
                aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
              <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                <li class="nav-item">
                  <a class="nav-link" data-bs-dismiss="offcanvas" routerLinkActive="active" routerLink="/actor">
                    <i class="fa-solid fa-users"></i> Actores
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-dismiss="offcanvas" routerLinkActive="active" routerLink="/film">
                    <i class="fa-solid fa-film"></i> Películas
                  </a>
                </li>
                <!-- Otros links si los necesitas -->
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </header>
  `,
  styles: [`
    /* Aquí puedes agregar estilos específicos para el header */
  `]
})
export class HeaderComponent { }
