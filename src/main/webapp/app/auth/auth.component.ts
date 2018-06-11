import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Principal } from '../shared/auth/principal.service';


@Component({
  selector: 'app-auth',
  template: `<router-outlet></router-outlet>`
})
export class AuthComponent implements OnInit {

  constructor(
    private principal: Principal,
    private router: Router,
  ) {}
  
  ngOnInit() {
    
  }

}