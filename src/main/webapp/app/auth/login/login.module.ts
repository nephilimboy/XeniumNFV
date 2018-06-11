/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, CUSTOM_ELEMENTS_SCHEMA, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";

import { LoginComponent } from './login.component';
import { LoginRoute } from './login.route';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,

        LoginRoute
    ],
    declarations: [
        LoginComponent
    ],
    providers: [
        // LoginService
    ],
    exports: [
    ]
})
export class LoginModule {
    // static forRoot(): ModuleWithProviders {
    //     return {
    //         ngModule: LoginModule,
    //         providers: [
    //             LoginService
    //         ]
    //     }
    // }
}
