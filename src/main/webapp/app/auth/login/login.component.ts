import { Component, OnInit, AfterViewInit, Renderer } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { EventManager } from 'ng-jhipster';

import { LoginService } from '../../shared/auth/login.service'
import { Principal } from '../../shared/auth/principal.service';
// import { errorMsg } from '../../blocks/config/loginErrorMsg';
// import { AlertToasterType } from '../../shared/alert/alert-toaster.enum';
import { VERSION } from '../../app.constants';

@Component({
    selector: 'frm-login',
    templateUrl: './login.component.html',
    styleUrls: ["./login.component.css"]
})
export class LoginComponent {
    public showCapcha: boolean = false;
    public capchaSrc: string = '';
    public capchaText: string = '';
    authenticationError: boolean;
    username: string;
    password: string;
    version: string;
    constructor(
        private loginService: LoginService,
        private renderer: Renderer,
        private principal: Principal,
        private router: Router,
        private http: Http,
        private eventManager: EventManager
    ) {
        this.version = VERSION;
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            (this.principal.isAuthenticated()) ? this.router.navigate(['/home']) : {};
        });
    }

    ngAfterViewInit() {
        //        this.renderer.invokeElementMethod( this.elementRef.nativeElement.querySelector( '#username' ), 'focus', [] );
    }
    login() {
        this.loginService.login({
            username: this.username,
            password: this.password,
            // jcaptcha: this.capchaText,
            // showCapcha: this.showCapcha
        }).then((res) => {
            this.authenticationError = false;
            // this.showCapcha = false;
            this.router.navigate(['/home']);
        }).catch((err) => {
            console.log(err);
            // console.log(err);
            // console.log(err.headers._headers.get('showcaptcha'));
            // if (err.headers._headers.get('showcaptcha')) {
            //     this.changeCaptcha();
            // }
            // this.eventManager.broadcast(
            //     {
            //         name: '',
            //         content: {
            //             message: errorMsg[err.headers._headers.get('frm.errorcode')],
            //             type: AlertToasterType.error
            //         }
            //     }
            // );
        });
    }

    changeCaptcha() {
        console.log('changeCaptcha')
        let randDate = new Date();
        let rand = randDate.getSeconds() + randDate.getMilliseconds();
        this.showCapcha = true;
        this.capchaSrc = 'j-captcha.jpg?rand' + rand;
    }
}

