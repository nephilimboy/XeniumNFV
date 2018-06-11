/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, CUSTOM_ELEMENTS_SCHEMA, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";

import { LoginModule } from './login/login.module';
import { AuthRoute } from './auth-routing.module';
import { AuthComponent } from './auth.component';
import {SharedCommonDirectiveModule} from '../shared/shared-common-directive.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        LoginModule,
        SharedCommonDirectiveModule,

        AuthRoute
    ],
    declarations: [
        AuthComponent,
    ],
    exports: [
    ],
})
export class AuthModule {

}
