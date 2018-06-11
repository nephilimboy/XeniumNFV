import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { I18nModule } from "../../shared/i18n/i18n.module";
import { MinifyMenuComponent } from "./minify-menu.component";
import { NavigationComponent } from "./navigation.component";
import { SmartMenuDirective } from "./smart-menu.directive";
import { RouterModule } from "@angular/router";
// import {ChatModule} from "../../chat/chat.module";

@NgModule({
    imports: [CommonModule, RouterModule, I18nModule],
    declarations: [
        MinifyMenuComponent,
        NavigationComponent,
        SmartMenuDirective
    ],
    exports: [
        MinifyMenuComponent,
        NavigationComponent,
        SmartMenuDirective
    ]
})
export class NavigationModule {}
