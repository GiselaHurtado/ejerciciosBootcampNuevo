import { Component } from '@angular/core';

@Component({
  selector: 'app-formularios',
  imports: [],
  templateUrl: './formularios.component.html',
  styleUrl: './formularios.component.css'
})
export class FormulariosComponent {
public elemento: any = { id: 1, nombre: 'Ian', apellido: 'Mikiku', correo: 'ianmik@gmail.com', fAlta: '2025-01-01', edad: 18, nif: '12345678z', activo: true };
public modo: 'add' | 'edit' = 'add';



}
