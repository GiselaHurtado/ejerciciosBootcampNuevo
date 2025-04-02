import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmService } from '../../services/film.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-film-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Listado de Películas</h2>
    <button (click)="addFilm()">Añadir Película</button>
    <table *ngIf="page">
      <thead>
        <tr>
          <th>ID</th>
          <th>Título</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let film of page.content">
          <td>{{ film.id }}</td>
          <td>{{ film.titulo }}</td>
          <td>
            <button (click)="editFilm(film)">Editar</button>
            <button (click)="deleteFilm(film)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination-controls" *ngIf="page">
      <button (click)="prevPage()" [disabled]="page.number === 0">Anterior</button>
      <span>Página {{ page.number + 1 }} de {{ page.totalPages }}</span>
      <button (click)="nextPage()" [disabled]="page.number >= page.totalPages - 1">Siguiente</button>
    </div>
    <p *ngIf="!page">No se han recibido datos.</p>
  `,
  styles: [`
    table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; }
    th, td { border: 1px solid #ccc; padding: 0.5rem; text-align: left; }
    .pagination-controls { text-align: center; margin: 1rem 0; }
  `]
})
export class FilmListComponent implements OnInit {
  page: any;
  pageSize: number = 3000;

  constructor(private filmService: FilmService, private router: Router) { }

  ngOnInit(): void {
    console.log("FilmListComponent inicializado");
    this.loadPage(0);
  }

  loadPage(pageNumber: number): void {
    console.log("Cargando página", pageNumber);
    this.filmService.getPaginatedFilms(pageNumber, this.pageSize, 'short').subscribe(
      res => {
        console.log("Respuesta recibida:", res);
        this.page = res;
      },
      error => console.error("Error al obtener películas paginadas", error)
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

  addFilm(): void {
    this.router.navigate(['/film/new']);
  }

  editFilm(film: any): void {
    this.router.navigate(['/film/edit', film.id]);
  }

  deleteFilm(film: any): void {
    if (confirm(`¿Estás seguro de eliminar la película ${film.titulo}?`)) {
      this.filmService.deleteFilm(film.id).subscribe(
        () => {
          alert('Película eliminada correctamente');
          this.loadPage(this.page.number);
        },
        error => {
          console.error("Error al eliminar película", error);
          alert("No se pudo eliminar la película");
        }
      );
    }
  }
}
