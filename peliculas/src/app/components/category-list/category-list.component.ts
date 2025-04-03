import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../../app/models/category.models';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Listado de Categorías</h2>
    <button (click)="addCategory()">Añadir Categoría</button>
    <table *ngIf="categories && categories.length">
      <thead>
        <tr>
          <th>ID</th>
          <th>Categoría</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let cat of categories">
          <td>{{ cat.id }}</td>
          <td>{{ cat.categoria }}</td>
          <td>
            <button (click)="editCategory(cat)">Editar</button>
            <button (click)="deleteCategory(cat)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p *ngIf="!categories || categories.length === 0">No hay categorías.</p>
  `,
  styles: [`
    table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; }
    th, td { border: 1px solid #ccc; padding: 0.5rem; text-align: left; }
  `]
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];

  constructor(private categoryService: CategoryService, private router: Router) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories = data;
        console.log('Categorías cargadas:', data);
      },
      error => console.error('Error al cargar categorías', error)
    );
  }

  addCategory(): void {
    this.router.navigate(['/category/new']);
  }

  editCategory(category: Category): void {
    this.router.navigate(['/category/edit', category.id]);
  }

  deleteCategory(category: Category): void {
    if (confirm(`¿Está seguro de eliminar la categoría ${category.categoria}?`)) {
      this.categoryService.deleteCategory(category.id).subscribe(
        () => {
          alert('Categoría eliminada correctamente');
          this.loadCategories();
        },
        error => {
          console.error('Error al eliminar categoría', error);
          alert('No se pudo eliminar la categoría');
        }
      );
    }
  }
}
