import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../../app/models/category.models';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  // Usamos la URL absoluta que ya estás utilizando para que se muestren los datos correctamente
  private baseUrl = 'http://localhost:8010/categorias/v1';

  constructor(private http: HttpClient) { }

  // Obtener todas las categorías (sin paginación)
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

  // Opcional: Obtener categorías paginadas (si tu backend lo soporta)
  getPaginatedCategories(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}?page=${page}&size=${size}`);
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.baseUrl}/${id}`);
  }

  createCategory(category: Category): Observable<any> {
    // Para creación, no enviamos el id ya que se genera automáticamente
    const payload = { categoria: category.categoria };
    return this.http.post(this.baseUrl, payload);
  }

  updateCategory(category: Category): Observable<any> {
    const payload = { id: category.id, categoria: category.categoria };
    return this.http.put(`${this.baseUrl}/${category.id}`, payload);
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
