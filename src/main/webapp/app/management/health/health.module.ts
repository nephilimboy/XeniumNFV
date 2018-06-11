import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HealthRoute } from "./health.route";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { HealthComponent } from "./health.component";
import { SharedCommonDirectiveModule } from "../../shared/shared-common-directive.module";
import { HealthModalComponent } from "./health-modal.component";
import { HealthService } from "./health.service";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        HealthRoute
    ],
    providers: [HealthService],
    declarations: [HealthComponent, HealthModalComponent],
    entryComponents: [HealthModalComponent]
})
export class HealthModule {}
