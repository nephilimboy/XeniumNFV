import {
    Component,
    OnInit,
    ViewChild,
    ElementRef,
    AfterViewInit,
    OnDestroy
} from "@angular/core";
import { Response } from "@angular/http";
import { ActivatedRoute, Router } from "@angular/router";
import { EventManager, PaginationUtil, ParseLinks } from "ng-jhipster";
import { Principal } from "../shared/auth/principal.service";
import { ResponseWrapper } from "../shared/model/response-wrapper.model";
import { PaginationConfig } from "../blocks/config/uib-pagination.config";
import { NotificationService } from "../shared/utils/notification.service";
import { Server } from "./server.model";
import { NetworkCard } from "./networkCard.model";

import { ServerService } from "./server.service";

@Component({
    selector: "app-server",
    templateUrl: "./server.component.html"
})
export class ServerComponent implements OnInit {
    public servers: Server[];
    currentAccount: any;
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
        private serverService: ServerService,
        private parseLinks: ParseLinks,
        // private alertService: AlertService,
        private principal: Principal,
        private eventManager: EventManager,
        private paginationUtil: PaginationUtil,
        private paginationConfig: PaginationConfig,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService
    ) {
        this.itemsPerPage = 10;
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

        this.serverService.update("", user).subscribe(response => {
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
        this.serverService
            .query("/getAll", {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
                (res: ResponseWrapper) => this.onError(res.json)
            );
    }

    trackIdentity(index, item: Server) {
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
        this.router.navigate(["/servers"], {
            queryParams: {
                page: this.page,
                sort: this.predicate + "," + (this.reverse ? "asc" : "desc")
            }
        });
        this.loadAll();
    }

    private onSuccess(servers, headers) {
        this.links = this.parseLinks.parse(headers.get("link"));
        this.totalItems = headers.get("X-Total-Count");
        this.queryCount = this.totalItems;
        this.servers = servers;
    }

    private onError(error) {
        // this.alertService.error(error.error, error.message, null);
    }

    viewServer(id: number) {}

    deleteServer(id: number) {
        this.notificationService.smartMessageBox(
            {
                title:
                    "<i class='fa fa-sign-out txt-color-orangeDark' style='font-family: 'IranSansNormal' !important;font-size: 9px;'></i> Delete This Server <span class='txt-color-orangeDark'><strong>" +
                    $("#show-shortcut").text() +
                    "</strong></span> ?",
                content: "",
                buttons: "[No][Yes]"
            },
            ButtonPressed => {
                if (ButtonPressed == "Yes") {
                    this.serverService.delete("/delete", id).subscribe(
                        (res: ResponseWrapper) => {
                            this.loadAll();
                        },
                        (res: ResponseWrapper) => {
                            console.log("Cant Delete Server");
                        }
                    );
                }
            }
        );
    }

    editServer(id: number) {}
}
