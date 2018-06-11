import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { HeaderModule } from "./header/header.module";
import { FooterComponent } from "./footer/footer.component";
import { NavigationModule } from "./navigation/navigation.module";
import { RibbonComponent } from "./ribbon/ribbon.component";
import { ShortcutComponent } from "./shortcut/shortcut.component";
// import {ToggleActiveDirective} from "../utils/toggle-active.directive";
import { LayoutSwitcherComponent } from "./layout-switcher.component";
import { MainComponent } from "./main/main.component";
import { RouterModule } from "@angular/router";
import { TooltipModule, BsDropdownModule } from "ngx-bootstrap";
import { RouteBreadcrumbsComponent } from "./ribbon/route-breadcrumbs.component";
import { UtilsModule } from "../shared/utils/utils.module";
import { SharedCommonDirectiveModule } from "../shared/shared-common-directive.module";

@NgModule({
    imports: [
        CommonModule,
        HeaderModule,
        NavigationModule,
        FormsModule,
        RouterModule,
        SharedCommonDirectiveModule,
        // UtilsModule,

        TooltipModule,
        BsDropdownModule
    ],
    declarations: [
        MainComponent,
        FooterComponent,
        RibbonComponent,
        ShortcutComponent,
        LayoutSwitcherComponent,
        RouteBreadcrumbsComponent
    ],
    exports: [
        MainComponent,
        HeaderModule,
        NavigationModule,
        FooterComponent,
        RibbonComponent,
        ShortcutComponent,
        LayoutSwitcherComponent
    ]
})
export class LayoutModule {}
