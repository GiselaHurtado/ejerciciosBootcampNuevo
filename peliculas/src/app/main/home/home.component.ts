import { Component } from '@angular/core';
import { ActorListComponent } from "../../components/actor-list/actor-list.component";

@Component({
  selector: 'app-home',
  imports: [ActorListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  title: string = 'Catálogo de Películas';

}
