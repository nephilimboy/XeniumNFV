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
import { Principal } from "../../shared/auth/principal.service";
import { UserComponent } from "./user.component";
import { ResolvePagingParams } from "../../shared/paginationParamResolver/resolvePagingParams.service";
import { UserCanActivate } from "../../shared/canActivate/userCanActivate.service";

const routes: Routes = [
    {
        path: "",
        component: UserComponent,
        resolve: {
            pagingParams: ResolvePagingParams
        },
        data: {
            pageTitle: "Users"
        }
        // canActivate: [UserCanActivate]
    }
];

export const UserRoute: ModuleWithProviders = RouterModule.forChild(
    routes
);
