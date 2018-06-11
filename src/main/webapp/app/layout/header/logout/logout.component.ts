/*
*
*    @ AH.GHORAB
*
*/
import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { NotificationService } from '../../../shared/utils/notification.service'
import { LoginService } from "../../../shared/auth/login.service";

declare var $: any;

@Component({
  selector: 'sa-logout',
  template: `
<div  id="logout" (click)="showPopup()" class="btn-header transparent pull-right">
        <span> <a style="background-color: #16A085 !important;
        border: 0px solid #16A085;" routerlink="/auth/login" title="Sign Out" data-action="userLogout" 
                  data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i
          class="fa fa-power-off "></i></a> </span>
    </div>
  `,
  styles: []
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router,
    private notificationService: NotificationService,
    private loginService: LoginService
  )
  { }

  showPopup() {
    this.notificationService.smartMessageBox({
      title: "<i class='fa fa-sign-out txt-color-orangeDark' style='font-family: 'IranSansNormal' !important;font-size: 9px;'></i> LogOut <span class='txt-color-orangeDark'><strong>" + $('#show-shortcut').text() + "</strong></span> ?",
      content: "",
      buttons: '[No][Yes]'

    }, (ButtonPressed) => {
      if (ButtonPressed == "Yes") {
        this.logout()
      }
    });
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/auth/login']);
  }
  ngOnInit() {
  }



}
