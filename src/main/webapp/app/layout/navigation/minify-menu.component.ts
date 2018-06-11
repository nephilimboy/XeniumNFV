import {Component} from '@angular/core';

import {LayoutService} from '../layout.service';

declare var $:any;

@Component({
  selector: 'sa-minify-menu',
  template: `<span style="background-color: #16A085 !important;
  color: white !important;" class="minifyme" (click)="toggle()">
    <i class="fa fa-arrow-circle-left hit"></i>
</span>`,
})
export class MinifyMenuComponent {

  constructor(private layoutService: LayoutService) {
  }

  toggle() {
    this.layoutService.onMinifyMenu()
  }
}
