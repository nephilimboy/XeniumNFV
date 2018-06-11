import { NgModule, ModuleWithProviders } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";

import { PopoverModule } from "ngx-popover";
import { LayoutModule } from "../layout/layout.module";
import { I18nModule } from "./i18n/i18n.module";
import { SmartadminWidgetsModule } from "./widgets/smartadmin-widgets.module";
import { UtilsModule } from "./utils/utils.module";

import { StateStorageService } from "./auth/state-storage.service";
import { AuthServerProvider } from "./auth/auth-session.service";
import { AccountService } from "./auth/account.service";
import { Principal } from "./auth/principal.service";
import { LoginService } from "./auth/login.service";
import { ResolvePagingParams } from "./paginationParamResolver/resolvePagingParams.service";
import { PaginationConfig } from "../blocks/config/uib-pagination.config";
import { UserCanActivate } from "./canActivate/userCanActivate.service";

@NgModule({
    imports: [CommonModule, FormsModule, RouterModule],
    declarations: [],
    exports: [
        CommonModule,
        FormsModule,
        RouterModule,

        // ModalModule,
        // ButtonsModule,
        // TooltipModule,
        // DropdownModule,
        // ProgressbarModule,
        // AlertModule,
        // TabsModule,
        // AccordionModule,
        // CarouselModule,

        PopoverModule,
        I18nModule,

        UtilsModule,

        // SmartadminFormsLiteModule,

        // SmartProgressbarModule,

        // InlineGraphsModule,

        SmartadminWidgetsModule

        // ChatModule,

        // StatsModule,

        // VoiceControlModule,
    ]
})
export class SharedModule {
    static forRoot(): ModuleWithProviders {
        return {
            ngModule: SharedModule,
            providers: [
                AccountService,
                Principal,
                AuthServerProvider,
                LoginService,
                StateStorageService,
                PaginationConfig,
                ResolvePagingParams,
                UserCanActivate
            ]
        };
    }
}
