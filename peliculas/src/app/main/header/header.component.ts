

import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <header>
      <nav class="navbar navbar-expand-lg navbar-light bg-light" fixed-top>
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
              <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
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
                <li class="nav-item">
                  <a class="nav-link" data-bs-dismiss="offcanvas" routerLinkActive="active" routerLink="/category">
                    <i class="fa-solid fa-layer-group"></i> Categorías
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-dismiss="offcanvas" routerLinkActive="active" routerLink="/language">
                    <i class="fa-solid fa-language"></i> Idiomas
                  </a>
                </li>
                
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </header>
  `,
  styles: [`
    .offcanvas-body {
      padding: 0; 
  justify-content: center;
 
  
    }
    .nav-link {
      padding: 1rem;
      font-size: 2rem;
      color: #333;
      
      
    }
    .nav-link:hover {
      background-color: #f8f9fa;
     
    }
  `]
})
export class HeaderComponent { }
