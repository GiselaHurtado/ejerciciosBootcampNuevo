import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Category } from 'src/app/models/category.models';
;

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>{{ isEdit ? 'Editar Categoría' : 'Agregar Categoría' }}</h2>
    <form (ngSubmit)="onSubmit()" #categoryForm="ngForm">
      <div>
        <label>Categoría:</label>
        <input type="text" [(ngModel)]="category.categoria" name="categoria" required>
      </div>
      <button type="submit">{{ isEdit ? 'Actualizar' : 'Crear' }}</button>
      <button type="button" (click)="cancel()">Cancelar</button>
    </form>
  `,
  styles: [`
    form div { margin: 0.5rem 0; }
  `]
})
export class CategoryFormComponent implements OnInit {
  category: Category = { id: 0, categoria: '' };
  isEdit: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEdit = true;
      const id = Number(idParam);
      this.categoryService.getCategoryById(id).subscribe(
        data => {
          this.category = data;
          console.log('Categoría cargada:', data);
        },
        error => console.error('Error al cargar categoría', error)
      );
    }
  }

  onSubmit(): void {
    if (this.isEdit) {
      this.categoryService.updateCategory(this.category).subscribe(
        () => {
          alert('Categoría actualizada');
          this.router.navigate(['/category']);
        },
        error => console.error('Error al actualizar categoría', error)
      );
    } else {
      this.categoryService.createCategory(this.category).subscribe(
        () => {
          alert('Categoría creada');
          this.router.navigate(['/category']);
        },
        error => console.error('Error al crear categoría', error)
      );
    }
  }

  cancel(): void {
    this.router.navigate(['/category']);
  }
}
