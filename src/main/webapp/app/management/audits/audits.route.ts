import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes,
    CanActivate,
    RouterModule
} from "@angular/router";
import { Injectable, ModuleWithProviders } from "@angular/core";
import { Route } from "@angular/router";

import { AuditsComponent } from "./audits.component";

export const auditsRoute: Routes = [
    {
        path: "",
        component: AuditsComponent,
        data: {
            pageTitle: "Audits"
        }
    }
];
export const AuditsRoute: ModuleWithProviders = RouterModule.forChild(
    auditsRoute
);
