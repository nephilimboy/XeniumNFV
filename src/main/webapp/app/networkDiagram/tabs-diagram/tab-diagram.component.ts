import { Component, Input } from "@angular/core";

@Component({
    selector: "tab-diagram",
    styles: [
        `
    .pane{
      padding: 1em;
    }
  `
    ],
    template: `
    <div [hidden]="!active" >
      <ng-content></ng-content>
      <ng-container *ngIf="template"
        [ngTemplateOutlet]="template"
        [ngTemplateOutletContext]="{ server: dataContext }"
      >
      </ng-container>
    </div>
  `
})
export class TabComponent {
    @Input("tabTitle") title: string;
    @Input() active = false;
    @Input() isCloseable = false;
    @Input() template;
    @Input() dataContext;
}
