import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { NotificationService } from 'src/app/main/common-services/notification.service';

@Component({
  selector: 'app-notification-modal',
  standalone: true,
  templateUrl: './notification-modal.component.html',
  imports: [NgClass]
})
export class NotificationModalComponent {
  constructor(private vm: NotificationService) { }

  public get VM() {
    return this.vm;
  }

  trackById(index: number, item: any): number {
    return item.Id;
  }
}
