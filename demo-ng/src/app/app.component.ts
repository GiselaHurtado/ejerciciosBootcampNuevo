import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from 'src/lib/my-core/services';
import { CommonModule } from '@angular/common';

import { NotificationModalComponent } from '.\src\app\main\notification-modal\notification-modal.component.ts';
import { DemosComponent } from './demos/demos.component';


@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, NotificationModalComponent, DemosComponent],
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