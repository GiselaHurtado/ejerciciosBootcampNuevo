import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmService } from '../../services/film.service';
import { Film } from '../../models/film.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FilmEditDTO } from '../../models/film-edit.dto';

@Component({
    selector: 'app-film-form',
    standalone: true,
    imports: [CommonModule, FormsModule],
    template: `
      <h2>{{ isEdit ? 'Editar Película' : 'Agregar Película' }}</h2>
      <form (ngSubmit)="onSubmit()" #filmForm="ngForm">
        <div>
          <label>Título:</label>
          <input type="text" [(ngModel)]="film.title" name="title" required>
        </div>
        <div>
          <label>Descripción:</label>
          <textarea [(ngModel)]="film.description" name="description"></textarea>
        </div>
        <div>
          <label>Duración (min):</label>
          <input type="number" [(ngModel)]="film.length" name="length">
        </div>
        <div>
          <label>Año de estreno:</label>
          <input type="number" [(ngModel)]="film.releaseYear" name="releaseYear">
        </div>
        <div>
          <label>Duración de alquiler (días):</label>
          <input type="text" [(ngModel)]="film.rentalDuration" name="rentalDuration" required>
        </div>
        <div>
          <label>Costo de alquiler:</label>
          <input type="number" [(ngModel)]="film.rentalRate" name="rentalRate" required>
        </div>
        <div>
          <label>Costo de reemplazo:</label>
          <input type="number" [(ngModel)]="film.replacementCost" name="replacementCost" required>
        </div>
        <div>
          <label>Idioma (ID):</label>
          <input type="number" [(ngModel)]="film.languageId" name="languageId" required>
        </div>
        <div>
          <label>Idioma original (ID):</label>
          <input type="number" [(ngModel)]="film.languageVOId" name="languageVOId">
        </div>
        <div>
          <label>Clasificación (G, PG, PG-13, R, NC-17):</label>
          <input type="text" [(ngModel)]="film.rating" name="rating">
        </div>
        <div>
          <label>Actores (IDs separados por comas):</label>
          <input type="text" [(ngModel)]="actorsInput" name="actors">
        </div>
        <div>
          <label>Categorías (IDs separados por comas):</label>
          <input type="text" [(ngModel)]="categoriesInput" name="categories">
        </div>
        <button type="submit">{{ isEdit ? 'Actualizar' : 'Crear' }}</button>
        <button type="button" (click)="cancel()">Cancelar</button>
      </form>
    `,
    styles: [`
      form div { margin: 0.5rem 0; }
    `]
  })
  export class FilmFormComponent implements OnInit {
    film: FilmEditDTO = {
      title: '',
      description: '',
      length: 0,
      rating: '',
      releaseYear: 1901,
      rentalDuration: '',
      rentalRate: 0,
      replacementCost: 0,
      languageId: 0,
      languageVOId: 0,
      actors: [],
      categories: []
    };
    actorsInput: string = '';
    categoriesInput: string = '';
    isEdit: boolean = false;
  
    constructor(
      private filmService: FilmService,
      private route: ActivatedRoute,
      private router: Router
    ) { }
  
    ngOnInit(): void {
      const idParam = this.route.snapshot.paramMap.get('id');
      if (idParam) {
        this.isEdit = true;
        const id = Number(idParam);
        this.filmService.getFilmById(id).subscribe(
          data => {
            this.film = data;
            if (data.actors) {
              this.actorsInput = data.actors.join(',');
            }
            if (data.categories) {
              this.categoriesInput = data.categories.join(',');
            }
          },
          error => console.error('Error al cargar la película', error)
        );
      }
    }
  
    onSubmit(): void {
      // Convertir cadenas a arrays de números
      this.film.actors = this.actorsInput.split(',')
        .map(item => Number(item.trim()))
        .filter(item => !isNaN(item));
      this.film.categories = this.categoriesInput.split(',')
        .map(item => Number(item.trim()))
        .filter(item => !isNaN(item));
  
      if (this.isEdit) {
        // El id se pasa en la URL. Se espera que el DTO tenga la propiedad "id" en el PUT.
        this.filmService.updateFilm(Number(this.route.snapshot.paramMap.get('id')), this.film).subscribe(
          () => {
            alert('Película actualizada');
            this.router.navigate(['/film']);
          },
          error => console.error('Error al actualizar película', error)
        );
      } else {
        this.filmService.createFilm(this.film).subscribe(
          () => {
            alert('Película creada');
            this.router.navigate(['/film']);
          },
          error => console.error('Error al crear película', error)
        );
      }
    }
  
    cancel(): void {
      this.router.navigate(['/film']);
    }
  }