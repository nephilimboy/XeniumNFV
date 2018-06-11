import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { UserRoute } from "./user.routing";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { UserComponent } from "./user.component";
import { SharedCommonDirectiveModule } from "../../shared/shared-common-directive.module";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        UserRoute
    ],
    providers: [],
    declarations: [UserComponent]
})
export class UserModule {}
