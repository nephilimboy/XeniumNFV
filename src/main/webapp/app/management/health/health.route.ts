import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes,
    CanActivate,
    RouterModule
} from "@angular/router";
import { Injectable, ModuleWithProviders } from "@angular/core";

import { HealthComponent } from "./health.component";

export const healthRoute: Routes = [
    {
        path: "",
        component: HealthComponent,
        data: {
            pageTitle: "Health"
        }
    }
];

export const HealthRoute: ModuleWithProviders = RouterModule.forChild(
    healthRoute
);
