import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HomeRoute } from "./home.routing";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { HomeComponent } from "./home.component";
import { SharedCommonDirectiveModule } from "../shared/shared-common-directive.module";
import { ServerStatusComponent } from "./serverStatus/serverStatus.component";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        HomeRoute
    ],
    declarations: [HomeComponent, ServerStatusComponent],
    entryComponents: [ServerStatusComponent]
})
export class HomeModule {}
