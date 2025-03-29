import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationService, NotificationType } from '../common-services/notification.service';

@Component({
  selector: 'app-demos',
  templateUrl: './demos.component.html'
})
export class DemosComponent implements OnInit, OnDestroy {

  private suscriptor: Subscription | undefined;

  constructor(public vm: NotificationService) { }

  ngOnInit(): void {
   
    this.suscriptor = this.vm.Notificacion.subscribe(n => {
      if (n.Type !== NotificationType.error) { return; }
      window.alert(`Suscripción: ${n.Message}`);
      
      this.vm.remove(this.vm.Listado.length - 1);
    });
  }

  ngOnDestroy(): void {
   
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }
}
