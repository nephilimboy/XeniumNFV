import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { SharedCommonDirectiveModule } from "../shared/shared-common-directive.module";
import { ServerComponent } from "./server.component";
import { ServerRoute } from "./server.routing";
import {
    ServerPopupComponent,
    ServerPopupOpennerComponent
} from "./server-edit.component";
import { ServerPopupService } from "./server-popup.service";
import { ServerService } from "./server.service";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        ServerRoute
    ],
    declarations: [
        ServerComponent,
        ServerPopupOpennerComponent,
        ServerPopupComponent
    ],
    entryComponents: [ServerPopupOpennerComponent, ServerPopupComponent],
    providers: [ServerPopupService, ServerService]
})
export class ServerModule {}
