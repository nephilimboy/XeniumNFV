import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ManagementRoute } from "./management.routing";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { ManagementComponent } from "./management.component";
import { SharedCommonDirectiveModule } from "../shared/shared-common-directive.module";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        ManagementRoute
    ],
    providers: [],
    declarations: [ManagementComponent]
})
export class ManagementModule {}
