// src/app/components/actor-form/actor-form.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../actores/actor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-actor-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>{{ isEdit ? 'Editar Actor' : 'Agregar Actor' }}</h2>
    <form (ngSubmit)="onSubmit()" #actorForm="ngForm">
      <div>
        <label>Nombre:</label>
        <input type="text" [(ngModel)]="actor.nombre" name="nombre" required>
      </div>
      <div>
        <label>Apellidos:</label>
        <input type="text" [(ngModel)]="actor.apellidos" name="apellidos" required>
      </div>
      <button type="submit">{{ isEdit ? 'Actualizar' : 'Crear' }}</button>
      <button type="button" (click)="cancel()">Cancelar</button>
    </form>
  `,
  styles: [`
    form div {
      margin: 0.5rem 0;
    }
  `]
})
export class ActorFormComponent implements OnInit {
  actor: Actor = { id: 0, nombre: '', apellidos: '' };
  isEdit = false;

  constructor(
    private actorService: ActorService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEdit = true;
      const id = Number(idParam);
      this.actorService.getActorById(id).subscribe(
        data => this.actor = data,
        error => console.error('Error al cargar actor', error)
      );
    }
  }

  onSubmit(): void {
    if (this.isEdit) {
      this.actorService.updateActor(this.actor).subscribe(
        () => {
          alert('Actor actualizado');
          this.router.navigate(['/']);
        },
        error => console.error('Error al actualizar actor', error)
      );
    } else {
      this.actorService.createActor(this.actor).subscribe(
        () => {
          alert('Actor creado');
          this.router.navigate(['/']);
        },
        error => console.error('Error al crear actor', error)
      );
    }
  }

  cancel(): void {
    this.router.navigate(['/']);
  }
}
