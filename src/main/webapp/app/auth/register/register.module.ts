/*
*
*    @ AH.GHORAB
*
*/
import {
    NgModule,
    CUSTOM_ELEMENTS_SCHEMA,
    ModuleWithProviders
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { RegisterComponent } from "./register.component";
import { RegisterRoute } from "./register.route";
import { RegisterService } from "./register.service";
import {SharedCommonDirectiveModule} from '../../shared/shared-common-directive.module';

@NgModule({
    imports: [
        CommonModule, 
        FormsModule, 
        SharedCommonDirectiveModule,
        RegisterRoute
    ],
    declarations: [RegisterComponent],
    providers: [RegisterService],
    exports: []
})
export class RegisterModule {}
