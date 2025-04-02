// src/app/components/actor-list/actor-list.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ActorService } from '../../actores/actor.service';
import { Page } from '../../models/page.models';
import { Actor } from '../../models/actor.model';

@Component({
  selector: 'app-actor-list',
  imports: [CommonModule, FormsModule],
 
  template: `
    <h2>Lista de Actores</h2>

    <!-- Buscador por ID -->
    <div class="search-bar">
      <input type="number" [(ngModel)]="searchId" placeholder="Buscar actor por ID">
      <button (click)="buscarActor()">Buscar</button>
      <button (click)="clearSearch()">Limpiar búsqueda</button>
    </div>

    <!-- Botón para agregar actor -->
    <div class="actions">
      <button (click)="addActor()">Añadir Actor</button>
    </div>

    <!-- Mostrar actor encontrado o listado paginado -->
    <ng-container *ngIf="busqueda; else listado">
      <div *ngIf="actorBuscado; else notFound">
        <p><strong>ID:</strong> {{ actorBuscado.id }}</p>
        <p><strong>Nombre:</strong> {{ actorBuscado.nombre }}</p>
        <p><strong>Apellidos:</strong> {{ actorBuscado.apellidos }}</p>
        <button (click)="editActor(actorBuscado)">Editar</button>
        <button (click)="deleteActor(actorBuscado)">Eliminar</button>
      </div>
      <ng-template #notFound>
        <p>No se encontró actor con ID {{ searchId }}</p>
      </ng-template>
      <button (click)="clearSearch()">Volver al listado</button>
    </ng-container>

    <ng-template #listado>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let actor of page?.content">
            <td>{{ actor.id }}</td>
            <td>{{ actor.nombre }}</td>
            <td>{{ actor.apellidos }}</td>
            <td>
              <button (click)="editActor(actor)">Editar</button>
              <button (click)="deleteActor(actor)">Eliminar</button>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="pagination-controls">
        <button (click)="prevPage()" [disabled]="page?.number === 0">Anterior</button>
        <span *ngIf="page"> Página {{ page.number + 1 }} de {{ page.totalPages }} </span>
        <button (click)="nextPage()" [disabled]="page && page.number === page.totalPages - 1">Siguiente</button>
      </div>
    </ng-template>
  `,
  styles: [`
    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 1rem;
    }
    th, td {
      border: 1px solid #ccc;
      padding: 0.5rem;
      text-align: left;
    }
    .pagination-controls, .search-bar, .actions {
      margin: 1rem 0;
      text-align: center;
    }
    .search-bar input {
      width: 150px;
      padding: 0.3rem;
    }
    button {
      margin: 0.2rem;
    }
  `]
})
export class ActorListComponent implements OnInit {
  page!: Page<Actor>;
  pageSize = 20;
  searchId: number | null = null;
  busqueda = false; // Flag para saber si estamos en modo búsqueda
  actorBuscado: Actor | null = null;

  constructor(private actorService: ActorService, private router: Router) { }

  ngOnInit(): void {
    this.loadPage(0);
  }

  loadPage(pageNumber: number): void {
    this.actorService.getPaginatedActors(pageNumber, this.pageSize).subscribe(
      res => {
        this.page = res;
        console.log('Página de actores recibida: ', res);
      },
      error => console.error('Error al obtener actores paginados', error)
    );
  }

  nextPage(): void {
    if (this.page && this.page.number < this.page.totalPages - 1) {
      this.loadPage(this.page.number + 1);
    }
  }

  prevPage(): void {
    if (this.page && this.page.number > 0) {
      this.loadPage(this.page.number - 1);
    }
  }

  buscarActor(): void {
    if (this.searchId != null) {
      this.busqueda = true;
      this.actorService.getActorById(this.searchId).subscribe(
        actor => {
          this.actorBuscado = actor;
          console.log('Actor encontrado: ', actor);
        },
        error => {
          console.error('Error al buscar actor', error);
          this.actorBuscado = null;
        }
      );
    }
  }

  clearSearch(): void {
    this.searchId = null;
    this.busqueda = false;
    this.actorBuscado = null;
    this.loadPage(0);
  }

  addActor(): void {
    // Redirige a un componente/formulario para agregar actor
    this.router.navigate(['/actor/new']);
  }

  editActor(actor: Actor): void {
    // Redirige a un componente/formulario para editar actor
    this.router.navigate(['/actor/edit', actor.id]);
  }

  deleteActor(actor: Actor): void {
    if (confirm(`¿Estás seguro de eliminar el actor ${actor.nombre} ${actor.apellidos}?`)) {
      this.actorService.deleteActor(actor.id).subscribe(
        () => {
          alert('Actor eliminado correctamente');
          // Recargar la página actual o la lista completa
          if (this.busqueda) {
            this.clearSearch();
          } else {
            this.loadPage(this.page.number);
          }
        },
        error => {
          console.error('Error al eliminar actor', error);
          alert('Error al eliminar actor');
        }
      );
    }
  }
}