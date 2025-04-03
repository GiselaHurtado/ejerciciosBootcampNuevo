import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LanguageService } from '../../services/language.service';
import { LanguageDTO } from '../../models/language.dto';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-language-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Listado de Idiomas</h2>
    <button (click)="addLanguage()">Añadir Idioma</button>
    <table *ngIf="languages && languages.length">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let lang of languages">
          <td>{{ lang.languageId }}</td>
          <td>{{ lang.name }}</td>
          <td>
            <button (click)="editLanguage(lang)">Editar</button>
            <button (click)="deleteLanguage(lang)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p *ngIf="!languages || languages.length === 0">No hay idiomas.</p>
  `,
  styles: [`
    table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; }
    th, td { border: 1px solid #ccc; padding: 0.5rem; text-align: left; }
  `]
})
export class LanguageListComponent implements OnInit {
  languages: LanguageDTO[] = [];

  constructor(private languageService: LanguageService, private router: Router) { }

  ngOnInit(): void {
    console.log("LanguageListComponent inicializado");
    this.loadLanguages();
  }

  loadLanguages(): void {
    this.languageService.getLanguages().subscribe(
      data => {
        this.languages = data;
        console.log('Idiomas cargados:', data);
      },
      error => console.error('Error al cargar idiomas', error)
    );
  }

  addLanguage(): void {
    this.router.navigate(['/language/new']);
  }

  editLanguage(language: LanguageDTO): void {
    this.router.navigate(['/language/edit', language.languageId]);
  }

  deleteLanguage(language: LanguageDTO): void {
    if (confirm(`¿Está seguro de eliminar el idioma ${language.name}?`)) {
      this.languageService.deleteLanguage(language.languageId).subscribe(
        () => {
          alert('Idioma eliminado correctamente');
          this.loadLanguages();
        },
        error => {
          console.error('Error al eliminar idioma', error);
          alert('No se pudo eliminar el idioma');
        }
      );
    }
  }
}
