import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LanguageService } from '../../services/language.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LanguageDTO } from '../../models/language.dto';

@Component({
  selector: 'app-language-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>{{ isEdit ? 'Editar Idioma' : 'Agregar Idioma' }}</h2>
    <form (ngSubmit)="onSubmit()" #languageForm="ngForm">
      <div>
        <label>Nombre del Idioma:</label>
        <input type="text" [(ngModel)]="language.name" name="name" required>
      </div>
      <button type="submit">{{ isEdit ? 'Actualizar' : 'Crear' }}</button>
      <button type="button" (click)="cancel()">Cancelar</button>
    </form>
  `,
  styles: [`
    form div { margin: 0.5rem 0; }
  `]
})
export class LanguageFormComponent implements OnInit {
  language: LanguageDTO = { languageId: 0, name: '' };
  isEdit: boolean = false;

  constructor(
    private languageService: LanguageService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEdit = true;
      const id = Number(idParam);
      this.languageService.getLanguageById(id).subscribe(
        data => {
          // Forzamos que el objeto tenga el languageId obtenido de la URL
          this.language = { ...data, languageId: id };
          console.log('Idioma cargado:', this.language);
        },
        error => console.error('Error al cargar el idioma', error)
      );
    }
  }

  onSubmit(): void {
    if (this.isEdit) {
      // Forzamos que el objeto tenga el languageId de la URL
      const idFromUrl = Number(this.route.snapshot.paramMap.get('id'));
      this.language.languageId = idFromUrl;
      this.languageService.updateLanguage(this.language).subscribe(
        () => {
          alert('Idioma actualizado');
          this.router.navigate(['/language']);
        },
        error => console.error('Error al actualizar idioma', error)
      );
    } else {
      this.languageService.createLanguage(this.language).subscribe(
        () => {
          alert('Idioma creado');
          this.router.navigate(['/language']);
        },
        error => console.error('Error al crear idioma', error)
      );
    }
  }

  cancel(): void {
    this.router.navigate(['/language']);
  }
}
