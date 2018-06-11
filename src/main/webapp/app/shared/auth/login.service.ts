/*
*
*    @ AH.GHORAB
*
*/
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

import { AuthServerProvider } from '../../shared/auth/auth-session.service';
import { Principal } from '../../shared/auth/principal.service';

@Injectable()
export class LoginService {
    constructor(
        private router: Router,
        private principal: Principal,
        private authServerProvider: AuthServerProvider
    ) { }
    login(credentials, callback?) {
        const cb = callback || function () { };
        return new Promise((resolve, reject) => {
            this.authServerProvider.login(credentials).subscribe((data) => {
                this.principal.identity(true).then((account) => {
                    console.log(account);
                    resolve(data);
                });
                return cb();
            }, (err) => {
                // this.logout();
                reject(err);
                return cb(err);
            });
        });
    }

    logout() {
        this.authServerProvider.logout().subscribe();
        setTimeout('', 1000);
        this.principal.authenticate(null);
    }
}
