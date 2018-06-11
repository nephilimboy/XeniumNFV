import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AuditsService } from "./audits.service";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { AuditsRoute } from "./audits.route";
import { SharedCommonDirectiveModule } from "../../shared/shared-common-directive.module";
import { AuditsComponent } from "./audits.component";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        AuditsRoute
    ],
    providers: [AuditsService],
    declarations: [AuditsComponent]
})
export class AuditsModule {}
