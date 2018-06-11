/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ResolvePagingParams } from "../shared/paginationParamResolver/resolvePagingParams.service";
import { NetworkDiagramComponent } from "./networkDiagram.component";

const routes: Routes = [
    {
        path: "",
        component: NetworkDiagramComponent,
        resolve: {
            pagingParams: ResolvePagingParams
        },
    }
];

export const networkDiagramRoute: ModuleWithProviders = RouterModule.forChild(
    routes
);
