/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { ServerComponent } from "./server.component";
import { ServerPopupOpennerComponent } from "./server-edit.component";
import { ResolvePagingParams } from "../shared/paginationParamResolver/resolvePagingParams.service";
import { UserCanActivate } from "../shared/canActivate/userCanActivate.service";

const routes: Routes = [
    {
        path: "",
        component: ServerComponent,
        resolve: {
            pagingParams: ResolvePagingParams
        },
        data: {},
        // canActivate: [UserCanActivate]
    },
    {
        path: "servers/edit/:id",
        component: ServerPopupOpennerComponent,
        outlet: "popup"
    },
    {
        path: "servers/new",
        component: ServerPopupOpennerComponent,
        outlet: "popup"
    }
];

export const ServerRoute: ModuleWithProviders = RouterModule.forChild(routes);
