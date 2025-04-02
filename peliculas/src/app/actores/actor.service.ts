import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page } from '../models/page.models';
import { Actor } from '../models/actor.model';


@Injectable({
  providedIn: 'root'
})
export class ActorService {
 
  private baseUrl = 'http://localhost:8010/actores/v1';

  constructor(private http: HttpClient) { }

  getPaginatedActors(page: number, size: number): Observable<Page<Actor>> {
    return this.http.get<Page<Actor>>(`${this.baseUrl}?page=${page}&size=${size}`);
  }
  
  getActorById(id: number): Observable<Actor> {
    return this.http.get<Actor>(`${this.baseUrl}/${id}`);
  }

  // Para crear actor, enviamos solo "nombre" y "apellidos"
  createActor(actor: Actor): Observable<any> {
    // Si el objeto actor tiene un id (por ejemplo, 0 o undefined), lo eliminamos
    const payload = {
      nombre: actor.nombre,
      apellidos: actor.apellidos
    };
    return this.http.post(`${this.baseUrl}`, payload);
  }

  // Para actualizar actor, enviamos el objeto con "id", "nombre" y "apellidos"
  updateActor(actor: Actor): Observable<any> {
    const payload = {
      id: actor.id,
      nombre: actor.nombre,
      apellidos: actor.apellidos
    };
    return this.http.put(`${this.baseUrl}/${actor.id}`, payload);
  }

  // DELETE se mantiene igual
  deleteActor(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}