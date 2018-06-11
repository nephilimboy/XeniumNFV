/*
*
*    @ AH.GHORAB
*
*/
import { Component, OnInit, AfterViewInit, ViewContainerRef, ViewChild, ElementRef, ComponentFactoryResolver, ComponentRef } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { EventManager } from 'ng-jhipster';
import { LocalStorageService } from 'ng2-webstorage';

import { LayoutService } from '../layout.service';
import { Principal } from '../../shared/auth/principal.service'


@Component({
    selector: 'mainLayout',
    templateUrl: './main.component.html'
})
export class MainComponent {
    constructor(
    ) {
    }

}



