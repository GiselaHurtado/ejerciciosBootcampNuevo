/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-empty-function */
/* eslint-disable @angular-eslint/no-empty-lifecycle-method */
import { Component, OnInit, OnDestroy, OnChanges, SimpleChanges, forwardRef, Input } from '@angular/core';
import { ActivatedRoute, Router, ParamMap, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { PaginatorModule } from 'primeng/paginator';
import { ErrorMessagePipe } from '@my/core';
import { ContactosViewModelService } from './servicios.service';
import { Subscription } from 'rxjs';
import { TypeValidator } from 'src/lib/my-core/directives/mis-validadores.directive';

@Component({
    selector: 'app-contactos',
    templateUrl: './tmpl-anfitrion.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [
        CommonModule,
        forwardRef(() => ContactosAddComponent),
        forwardRef(() => ContactosEditComponent),
        forwardRef(() => ContactosViewComponent),
        forwardRef(() => ContactosListComponent),
    ]
})
export class ContactosComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.load();
  }
  ngOnDestroy(): void { 
    this.vm.clear(); 
  }
}

@Component({
    selector: 'app-contactos-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [CommonModule, RouterLink, PaginatorModule]
})
export class ContactosListComponent implements OnChanges, OnDestroy {
  @Input() page: number = 0;

  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.load(this.page);
  }
  ngOnDestroy(): void { 
    this.vm.clear(); 
  }
}

@Component({
    selector: 'app-contactos-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [CommonModule, FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosAddComponent implements OnInit {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
    selector: 'app-contactos-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [CommonModule, FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(
    protected vm: ContactosViewModelService,
    protected route: ActivatedRoute, 
    protected router: Router
  ) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.edit(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}

@Component({
    selector: 'app-contactos-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [CommonModule, DatePipe]
})
export class ContactosViewComponent implements OnChanges {
  @Input() id!: string;
  constructor(protected vm: ContactosViewModelService, protected router: Router) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const CONTACTOS_COMPONENTES = [
  // ContactosComponent,  // Componente principal ya se importa en otros lugares si es necesario.
  ContactosListComponent, 
  ContactosAddComponent, 
  ContactosEditComponent, 
  ContactosViewComponent,
];
