import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationService, NotificationType } from '../common-services/notification.service';

@Component({
  selector: 'app-demos',
  templateUrl: './demos.component.html'
})
export class DemosComponent implements OnInit, OnDestroy {

  private fecha = new Date('2025-03-31');

  constructor(public vm: NotificationService) { }

  public get Fecha(): string { return this.fecha.toISOString(); }
  public set Fecha(value: string) {
     this.fecha = new Date(value);
     }


  private suscriptor: Subscription | undefined;

  

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
