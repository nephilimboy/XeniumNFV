import {Component, ViewContainerRef} from '@angular/core';

@Component({
    selector: 'app-root',
    template: `
        <!--<ng4-loading-spinner></ng4-loading-spinner>-->
        <router-outlet></router-outlet>
        <router-outlet name="popup"></router-outlet>
    `
})

export class AppComponent {

    public constructor(private viewContainerRef: ViewContainerRef) {
    }

}
