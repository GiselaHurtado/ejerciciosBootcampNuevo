import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from 'src/lib/my-core/services/logger.service';
import { DemosComponent } from 'src/app/demos/demos.component';
import { NotificationComponent } from './main/notification/notification.component';
import { NotificationModalComponent } from './main/notification-modal/notification-modal.component';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, DemosComponent, NotificationModalComponent, NotificationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'Sistema de Notificaciones';

  constructor(out: LoggerService) {
    out.error('Es es un Error ');
    out.warn('Es es un warn');
    out.info('Es es un info');
    out.log('Es es un log');
  }

 
}