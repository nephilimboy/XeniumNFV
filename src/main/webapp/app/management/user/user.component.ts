import { Component, OnInit, OnDestroy } from "@angular/core";
import { Response } from "@angular/http";
import { ActivatedRoute, Router } from "@angular/router";

import { EventManager, PaginationUtil, ParseLinks } from "ng-jhipster";

import { Principal } from "../../shared/auth/principal.service";
import { User } from './user.model';
import { UserService } from "../../shared/user/user.service";
import { ResponseWrapper } from "../../shared/model/response-wrapper.model";


import { PaginationConfig } from "../../blocks/config/uib-pagination.config";

export const ITEMS_PER_PAGE = 10;

@Component({
    selector: "app-home",
    templateUrl: "./user.component.html"
})
export class UserComponent implements OnInit, OnDestroy {
    currentAccount: any;
    users: User[];
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private userService: UserService,
        private parseLinks: ParseLinks,
        // private alertService: AlertService,
        private principal: Principal,
        private eventManager: EventManager,
        private paginationUtil: PaginationUtil,
        private paginationConfig: PaginationConfig,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data["pagingParams"].page;
            this.previousPage = data["pagingParams"].page;
            this.reverse = data["pagingParams"].ascending;
            this.predicate = data["pagingParams"].predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadAll();
            this.registerChangeInUsers();
        });
    }

    ngOnDestroy() {
        this.routeData.unsubscribe();
    }

    registerChangeInUsers() {
        this.eventManager.subscribe("userListModification", response =>
            this.loadAll()
        );
    }

    setActive(user, isActivated) {
        user.activated = isActivated;

        this.userService.update(user).subscribe(response => {
            if (response.status === 200) {
                this.error = null;
                this.success = "OK";
                this.loadAll();
            } else {
                this.success = null;
                this.error = "ERROR";
            }
        });
    }

    loadAll() {
        this.userService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
                (res: ResponseWrapper) => this.onError(res.json)
            );
    }

    trackIdentity(index, item: User) {
        return item.id;
    }

    sort() {
        const result = [this.predicate + "," + (this.reverse ? "asc" : "desc")];
        if (this.predicate !== "id") {
            result.push("id");
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(["/user-management"], {
            queryParams: {
                page: this.page,
                sort: this.predicate + "," + (this.reverse ? "asc" : "desc")
            }
        });
        this.loadAll();
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get("link"));
        this.totalItems = headers.get("X-Total-Count");
        this.queryCount = this.totalItems;
        this.users = data;
    }

    private onError(error) {
        // this.alertService.error(error.error, error.message, null);
    }
}
