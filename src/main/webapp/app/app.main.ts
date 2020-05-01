/*
*
*    @ AH.GHORAB @ amir-kusedghi
*
*/
import { platformBrowserDynamic } from "@angular/platform-browser-dynamic";

import { AppModule } from "./app.module";

if (module["hot"]) {
    module["hot"].accept();
}

platformBrowserDynamic().bootstrapModule(AppModule);
