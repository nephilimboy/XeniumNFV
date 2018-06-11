import { Component, OnInit, OnDestroy } from "@angular/core";
import { Response } from "@angular/http";
import { ActivatedRoute, Router } from "@angular/router";
import { EventManager, PaginationUtil, ParseLinks } from "ng-jhipster";
import { Principal } from "../shared/auth/principal.service";
import { UserService } from "../shared/user/user.service";
import { ResponseWrapper } from "../shared/model/response-wrapper.model";
import { PaginationConfig } from "../blocks/config/uib-pagination.config";

@Component({
    selector: "app-home",
    templateUrl: "./management.component.html"
})
export class ManagementComponent {
    public constructor(
    ){}
   
}
