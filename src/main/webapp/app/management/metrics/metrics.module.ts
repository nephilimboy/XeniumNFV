import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MetricsService } from "./metrics.service";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { MetricsRoute } from "./metrics.route";
import { SharedCommonDirectiveModule } from "../../shared/shared-common-directive.module";
import { MetricsComponent } from "./metrics.component";
import { MetricsModalComponent } from "./metrics-modal.component";

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        SharedCommonDirectiveModule,
        MetricsRoute
    ],
    providers: [MetricsService],
    declarations: [MetricsComponent, MetricsModalComponent],
    entryComponents: [MetricsModalComponent]
})
export class MetricsModule {}
