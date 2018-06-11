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
    RouterModule,
    Router
} from "@angular/router";
import { PaginationUtil } from "ng-jhipster";
import { Principal } from "../auth/principal.service";

@Injectable()
export class UserCanActivate implements CanActivate {
    constructor(private principal: Principal, private router: Router) {}

    canActivate() {
        console.log('in can activate');
        return this.principal.identity().then(account => {
            if (this.principal.hasAnyAuthority(["ROLE_USER"])) {
                console.log('in can activate true;')
                return true;
            } else {
                console.log('in can activate false;')
                this.router.navigate(["/auth/login"]);
                return false;
            }
        });
    }
}
