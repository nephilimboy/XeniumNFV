/*
*
*    @ AH.GHORAB
*
*/
import { Routes, RouterModule } from "@angular/router";
import { NgModule, ModuleWithProviders } from "@angular/core";

import { MainComponent } from "./layout/main/main.component";
import { HomeModule } from "./home/home.module";
import { AuthModule } from "./auth/auth.module";

const LAYOUT_ROUTES = [
    // ...errorRoute
];

/*
We have just 3 main module 
1- Auth
2- Home
3- Management
so all other compunent(s) or module(s) will be declared inside these 3 modules so 
there is no need to add any of them here, also i used const inside eatch route 
instead of module so all routing modules is become const route
*/

// export const nodeModuleLoginRoute: Routes =
//     [{
//         path: 'auth',
//         loadChildren: './auth/auth.module#AuthModule'
//     }];
export const routes: Routes = [
    { path: "", redirectTo: "/auth", pathMatch: "full" },
    {
        path: "",
        component: MainComponent,
        children: [
            {
                path: "home",
                loadChildren: "./home/home.module#HomeModule",
                data: {
                    pageTitle: "Home"
                }
                // canActivate: [RouteActivationServcie]
            },
            {
                path: "networkDiagram",
                loadChildren:
                    "./networkDiagram/networkDiagram.module#NetworkDiagramModule",
                data: {
                    pageTitle: "Network Diagram",
                }
                // canActivate: [RouteActivationServcie]
            },
            {
                path: "servers",
                loadChildren: "./servers/server.module#ServerModule",
                data: {
                    pageTitle: "Servers",
                    // reuseRouteKey: "/servers/servers"
                }
                // canActivate: [RouteActivationServcie]
            },
            {
                path: "management",
                loadChildren: "./management/management.module#ManagementModule",
                data: {
                    pageTitle: "management",
                }
                // canActivate: [RouteActivationServcie]
            }
        ]
        // canActivate: [RouteActivationServcie]
    },

    { path: "**", redirectTo: "" }
];

export const AppRoute: ModuleWithProviders = RouterModule.forRoot(routes, {
    useHash: true
});
