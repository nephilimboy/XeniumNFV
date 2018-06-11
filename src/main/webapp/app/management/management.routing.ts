/*
*
*    @ AH.GHORAB
*
*/
import { Injectable, ModuleWithProviders } from "@angular/core";
import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes,
    CanActivate,
    RouterModule
} from "@angular/router";

import { PaginationUtil } from "ng-jhipster";
import { Principal } from "../shared/auth/principal.service";
import { ManagementComponent } from "./management.component";
import { ResolvePagingParams } from "../shared/paginationParamResolver/resolvePagingParams.service";
import { UserCanActivate } from "../shared/canActivate/userCanActivate.service";

const routes: Routes = [
    {
        path: 'users',
        loadChildren: './user/user.module#UserModule',
        data:{
        }
    },
    {
        path: 'health',
        loadChildren: './health/health.module#HealthModule',
        data:{
        }
    },
    {
        path: 'metrics',
        loadChildren: './metrics/metrics.module#MetricsModule',
        data:{
        }
    },
    {
        path: 'audits',
        loadChildren: './audits/audits.module#AuditsModule',
        data:{
        }
    },
    
];

export const ManagementRoute: ModuleWithProviders = RouterModule.forChild(
    routes
);
