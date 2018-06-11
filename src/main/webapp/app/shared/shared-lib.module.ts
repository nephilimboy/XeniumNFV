import { NgModule, ModuleWithProviders } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TabsModule, TooltipModule } from 'ngx-bootstrap'
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { ToastModule, ToastOptions } from 'ng2-toastr/ng2-toastr';
import { CarouselModule } from "ngx-bootstrap";
// import { UtilsModule } from './utils';
import { NgbDateStruct, NgbCalendar, NgbDatepickerI18n, NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgbCalendarPersian } from "ng2-datepicker-jalali/persian/ngb-calendar-persian";
import { NgbDatepickerI18nPersian } from "ng2-datepicker-jalali/persian/ngb-datepicker-i18n-persian";
import { Ng2Webstorage } from "ng2-webstorage";
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';



export class CustomOption extends ToastOptions {
    animate = 'fade'; // you can pass any options to override defaults
    newestOnTop = false;
    showCloseButton = true;
    dismiss = 'auto';
    toastLife = 4000;
    positionClass = 'toast-top-center';
}


@NgModule({
    imports: [
        NgbModule.forRoot(),
        NgJhipsterModule.forRoot({
            i18nEnabled: true,
            defaultI18nLang: 'en'
        }),
        Ng2Webstorage.forRoot({
            prefix: '',
            separator: '.'
        }),
        //Adding Toaster
        ToastModule.forRoot(),
        // Adding CarouselModule
        CarouselModule.forRoot(),
        // Tab Component module
        TabsModule.forRoot(),
        // ToolTip Component module
        TooltipModule.forRoot(),

    ],
    exports: [
    ],
    providers: [
        {
            //Adding Toaster
            provide: ToastOptions,
            useClass: CustomOption
        },
        { provide: NgbCalendar, useClass: NgbCalendarPersian },
        { provide: NgbDatepickerI18n, useClass: NgbDatepickerI18nPersian }
    ]
})
export class SharedLibsModule {
}

