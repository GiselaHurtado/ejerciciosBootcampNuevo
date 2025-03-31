import { Component, OnInit, OnDestroy, signal, computed } from '@angular/core';
import { Subscription, Unsubscribable } from 'rxjs';
import { NotificationService, NotificationType } from '../common-services/notification.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-demos',
  imports: [FormsModule, CommonModule],
  templateUrl: './demos.component.html'
})
export class DemosComponent implements OnInit, OnDestroy {
  private fecha = new Date('2025-03-31');
  public readonly nombre = signal<string>('mundo')
  public readonly fontSize = signal<number>(22)
  public readonly listado = signal([
    { id: 1, nombre: 'Juan' },
    { id: 2, nombre: 'OVIEDO' },
    { id: 3, nombre: 'barcelona' },
    { id: 3, nombre: 'Ciudad Real' },
    
  ])
  public readonly idProvincia = signal<number>(2)

  public resultado = signal<string>('')
  public visible = signal<boolean>(true)
  public invisible = computed<boolean>(() => this.visible())
  public readonly estetica = signal({ importante:true, urgente: true, error: false })	


  constructor(public vm: NotificationService) { }

  public get Fecha(): string { return this.fecha.toISOString(); }
  public set Fecha(value: string) {
     this.fecha = new Date(value);
     }


saluda(){
  this.resultado.set(`Hola ${this.nombre()}`)
  
}
despide(){
  this.resultado.set(`Adios ${this.nombre()}`)
 
}
di(algo: string){ 
  this.resultado.set(`Dice ${algo}`)
 
}
cambia(){
  this.visible.update(valor => !valor)
  this.estetica.update(valor => ({...valor, error: !valor.error})) //desestructing
}

add(provincia: string) {
  const id = this.listado()[this.listado().length - 1].id + 1;
  this.listado.update(valor => [...valor, { id, nombre: provincia }])
  this.idProvincia.set(id);
}

calcula(a: number, b: number ) { return a + b; }



  private suscriptor: Subscription | undefined;
 // private suscriptor?: Unsubscribable;

  

  ngOnInit(): void {
   
    this.suscriptor = this.vm.Notificacion.subscribe(n => {
      if (n.Type !== NotificationType.error) { return; }
      window.alert(`Suscripción: ${n.Message}`);
      
      this.vm.remove(this.vm.Listado.length - 1);
    });
    complete: () => this.suscriptor?.unsubscribe();
  }

  ngOnDestroy(): void {
   
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }
}
