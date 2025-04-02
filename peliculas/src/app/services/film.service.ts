import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FilmShortDTO } from '../models/film-short.dto';
import { FilmEditDTO } from '../models/film-edit.dto';

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  
  private baseUrl = 'http://localhost:8010/peliculas/v1';

  constructor(private http: HttpClient) { }

  // Listado paginado en modo short (devuelve FilmShortDTO)
  getPaginatedFilms(page: number, size: number, p0: string): Observable<any> {
    // Se espera que el backend devuelva un objeto con { content: FilmShortDTO[], number, totalPages, ... }
    return this.http.get<any>(`${this.baseUrl}?page=${page}&size=${size}&mode=short`);
  }

  // Obtener película para edición (FilmEditDTO)
  getFilmById(id: number): Observable<FilmEditDTO> {
    return this.http.get<FilmEditDTO>(`${this.baseUrl}/${id}?mode=edit`);
  }

  // Crear película. El backend espera FilmEditDTO sin el id.
  createFilm(film: FilmEditDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}`, film);
  }

  // Actualizar película. Se espera que el id del DTO coincida con la URL.
  updateFilm(id: number, film: FilmEditDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, film);
  }

  // Eliminar película por id.
  deleteFilm(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
