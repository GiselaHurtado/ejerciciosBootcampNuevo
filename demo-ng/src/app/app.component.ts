import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from 'src/lib/my-core/services';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'world';

  constructor(out: LoggerService) {
    out.error('Es es un Error ');
    out.warn('Es es un warn');
    out.info('Es es un info');
    out.log('Es es un log');
  }
}