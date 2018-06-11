import "./vendor.ts";
import { NgModule, ApplicationRef } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { AppRoute, routes } from "./app.route";
import { AppComponent } from "./app.component";
import { APP_RESOLVER_PROVIDERS } from "./app.resolver";
import { AppState, InternalStateType } from "./app.service";
import { CoreModule } from "./core/core.module";
import { LayoutModule } from "./layout/layout.module";
import { SharedModule } from "./shared/shared.module";
import { SharedLibsModule } from "./shared/shared-lib.module";
import { SharedCommonDirectiveModule } from "./shared/shared-common-directive.module";
import { RouterModule, RouteReuseStrategy } from "@angular/router";
import { ModalModule } from "ngx-bootstrap/modal";
import { AuthModule } from "./auth/auth.module";
import { customHttpProvider } from "./blocks/interceptor/http.provider";
import { HomeModule } from "./home/home.module";
import { ServerModule } from "./servers/server.module";
import { NetworkDiagramModule } from "./networkDiagram/networkDiagram.module";
import { ManagementModule } from "./management/management.module";
const APP_PROVIDERS = [...APP_RESOLVER_PROVIDERS, AppState];

type StoreType = {
    state: InternalStateType;
    restoreInputValues: () => void;
    disposeOldHosts: () => void;
};

/**
 * `AppModule` is the main entry point into Angular2's bootstraping process
 */
@NgModule({
    bootstrap: [AppComponent],
    declarations: [AppComponent],
    imports: [
        BrowserModule,
        RouterModule,
        BrowserAnimationsModule, //@SmartAdmin
        CommonModule,
        FormsModule,

        // ModalModule.forRoot(),

        SharedModule.forRoot(),
        SharedLibsModule,
        SharedCommonDirectiveModule,

        CoreModule,
        LayoutModule,
        AuthModule,
        HomeModule,
        ServerModule,
        NetworkDiagramModule,
        ManagementModule,

        AppRoute
    ],
    exports: [],
    providers: [
        // expose our Services and Providers into Angular's dependency injection
        // ENV_PROVIDERS,
        APP_PROVIDERS
    ]
})
export class AppModule {
    constructor(public appRef: ApplicationRef, public appState: AppState) {
        console.log("d");
    }
}
