import { NgModule, Sanitizer } from "@angular/core";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { NgJhipsterModule } from "ng-jhipster";
import { ToastModule } from "ng2-toastr/ng2-toastr";
import { FormsModule } from "@angular/forms";
import { TabsModule } from "ngx-bootstrap";
import { RouterModule, RouteReuseStrategy } from "@angular/router";
import {DatePipe} from '@angular/common';

import { SmartadminWidgetsModule } from "./widgets/smartadmin-widgets.module";
// import { ValidationDirectiveModule } from './validation/validation.module';
import { CarouselModule, TooltipModule } from "ngx-bootstrap";
import { UtilsModule } from "./utils/utils.module";
// import { BaseInformationDirective } from './directives/baseInformation/baseInformation.directive';
// import { CoolClockComponent } from './components/coolClock/coolClock.component';
// import { PersianCalendarComponent } from './components/persianCalendar/persianCalendar.component';
// import { HasAnyAuthorityDirective } from './directives/hasAnyAuthority/has-any-authority.directive';
// import { SmartadminGalleryModule } from './utils/gallery/gallery.module';
// import { OnOffSwitchModule } from './utils/on-off-switch/on-off-switch.module';
import { customHttpProvider } from "../blocks/interceptor/http.provider";
// import { PaginationComponent } from './pagination/pagination.component';
// import { DropzoneModule } from './utils/forms/dropzone/dropzone.module';
// import { BHFileUploadModule } from './fileUpload/fileUpload.module'; // Baharan File Uploader
// import { BhSearchFilter } from './directives/searchFilter/searchFileter.directive';
// import { BhSearchProperty } from './directives/searchFilter/searchProperty.directive';
// import { IframeComponent } from './iframe/iframe.component';
// import { KeysPipe } from './utils/pipes/keys/keys.pipe';
// import { TestiComponent } from './directives/searchFilter/testi.component';
import { BigBreadcrumbsComponent } from "../layout/navigation/big-breadcrumbs.component";
import { InlineGraphsModule } from "./graphs/inline/inline-graphs.module";

import { FlotChartModule } from "./graphs/flot-chart/flot-chart.module";
import { D3Module } from "./graphs/d3/d3.module";
import { OnOffSwitchModule } from "./forms/input/on-off-switch/on-off-switch.module";
// import { CustomReuseStrategy } from '../blocks/routeStrategy/customReuseStrategy';
import { PasswordStrengthBarComponent } from "./passwordBar/password-strength-bar.component";
import { KnobDirective } from "./forms/input/knob.directive";
import { AsideModule } from "ng2-aside";
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';

@NgModule({
    imports: [
        FormsModule,
        UtilsModule,
        SmartadminWidgetsModule,
        // ValidationDirectiveModule,
        NgJhipsterModule,
        NgbModule,
        ToastModule,
        CarouselModule,
        TabsModule,
        TooltipModule,
        // SmartadminGalleryModule,
        // OnOffSwitchModule,
        // DropzoneModule,
        // BHFileUploadModule,
        FlotChartModule,
        D3Module,
        OnOffSwitchModule,
        InlineGraphsModule,
        AsideModule,
        Ng4LoadingSpinnerModule
    ],
    declarations: [
        BigBreadcrumbsComponent,
        PasswordStrengthBarComponent,
        // BaseInformationDirective,
        // CoolClockComponent,
        // PersianCalendarComponent,
        // HasAnyAuthorityDirective,
        // PaginationComponent,
        // BhSearchFilter,
        // BhSearchProperty,
        // IframeComponent,
        // KeysPipe,
        // TestiComponent,
        KnobDirective
    ],
    providers: [
        DatePipe,
        customHttpProvider()
        // {
        //     provide: RouteReuseStrategy,
        //     useClass: CustomReuseStrategy
        // }
    ],
    exports: [
        DatePipe,
        // UtilsModule,
        SmartadminWidgetsModule,
        // ValidationDirectiveModule,
        NgJhipsterModule,
        NgbModule,
        ToastModule,
        // BaseInformationDirective,
        // CoolClockComponent,
        // PersianCalendarComponent,
        CarouselModule,
        TabsModule,
        // HasAnyAuthorityDirective,
        TooltipModule,
        // SmartadminGalleryModule,
        // OnOffSwitchModule,
        // PaginationComponent,
        // DropzoneModule,
        // BHFileUploadModule,
        // BhSearchFilter,
        // BhSearchProperty,
        // IframeComponent,
        // KeysPipe,
        // TestiComponent
        BigBreadcrumbsComponent,
        FlotChartModule,
        D3Module,
        OnOffSwitchModule,
        InlineGraphsModule,
        PasswordStrengthBarComponent,
        KnobDirective,
        AsideModule,
        Ng4LoadingSpinnerModule
    ]
})
export class SharedCommonDirectiveModule {}
