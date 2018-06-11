import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes,
    CanActivate,
    RouterModule
} from "@angular/router";
import { Injectable, ModuleWithProviders } from "@angular/core";

import { MetricsComponent } from "./metrics.component";

export const metricsRoute: Routes = [
    {
        path: "",
        component: MetricsComponent,
        data: {
            pageTitle: "Metrics"
        }
    }
];

export const MetricsRoute: ModuleWithProviders = RouterModule.forChild(
    metricsRoute
);
