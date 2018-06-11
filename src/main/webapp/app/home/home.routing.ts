/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserCanActivate } from "../shared/canActivate/userCanActivate.service";
import { ResolvePagingParams } from "../shared/paginationParamResolver/resolvePagingParams.service";

import { HomeComponent } from "./home.component";

const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
        resolve: {
            pagingParams: ResolvePagingParams
        },
        // canActivate: [UserCanActivate]
    }
];

export const HomeRoute: ModuleWithProviders = RouterModule.forChild(routes);
