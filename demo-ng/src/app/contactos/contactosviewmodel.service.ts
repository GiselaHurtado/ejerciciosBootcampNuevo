import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  

  constructor() { }
}

export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';