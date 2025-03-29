import { Component } from '@angular/core';


import { I18nSelectPipe } from '@angular/common';
import { NotificationService } from 'src/app/common-services/notification.service';

@Component({
  selector: 'app-notification',
  standalone: true,
  templateUrl: './notification.component.html',
  imports: [I18nSelectPipe]
})
export class NotificationComponent {
  constructor(private vm: NotificationService) { }


  public get VM() {
    return this.vm;
  }

 
  trackById(index: number, item: any): number {
    return item.Id;
  }

  
  getIndex(item: any): number {
    return this.vm.Listado.indexOf(item);
  }
}
