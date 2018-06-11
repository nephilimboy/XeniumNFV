import {
    Component,
    OnInit,
    ViewChild,
    ElementRef,
    AfterViewInit,
    ComponentRef,
    ComponentFactoryResolver
} from "@angular/core";
import {
    ContentChildren,
    QueryList,
    AfterContentInit,
    ViewContainerRef
} from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { EventManager, PaginationUtil, ParseLinks } from "ng-jhipster";
import { UserService } from "../shared/user/user.service";
import { ServerStatusComponent } from "./serverStatus/serverStatus.component";
import { ServerService } from "../servers/server.service";
import { Server } from "../servers/server.model";
import { ResponseWrapper } from "../shared/model/response-wrapper.model";

@Component({
    selector: "app-home",
    templateUrl: "./home.component.html",
    styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit, AfterViewInit {


   











    // @ViewChild("xnetServerStatus") xnetServerStatusTemplate;
    @ViewChild('xnetServerStatus', {read: ViewContainerRef}) xnetServerStatusTemplate :ViewContainerRef;
    public servers: Server[];
    public serverStatusArr: ServerStatusComponent[] = [];
    currentAccount: any;
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    itemsPerPage: any;

    constructor(
        private componentFactoryResolver: ComponentFactoryResolver,
        private serverService: ServerService,
        private parseLinks: ParseLinks,
        private activatedRoute: ActivatedRoute
    ) {
        this.itemsPerPage = 100;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data["pagingParams"].page;
            this.previousPage = data["pagingParams"].page;
            this.reverse = data["pagingParams"].ascending;
            this.predicate = data["pagingParams"].predicate;
        });
        this.loadAll();
    }

    ngOnInit() {
        // this.serverStatusArr.push(this.xnetServerStatusTemplate);
        // this.serverStatusArr.push(this.xnetServerStatusTemplate);
        // this.serverStatusArr.push(this.xnetServerStatusTemplate);
    }

    ngAfterViewInit() {
        
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

    sort() {
        const result = [this.predicate + "," + (this.reverse ? "asc" : "desc")];
        if (this.predicate !== "id") {
            result.push("id");
        }
        return result;
    }

    private onSuccess(servers, headers) {
        this.links = this.parseLinks.parse(headers.get("link"));
        this.totalItems = headers.get("X-Total-Count");
        this.queryCount = this.totalItems;
        this.servers = servers;
        this.createServerStatusWidget(this.servers);
    }

    private onError(error) {}

    createServerStatusWidget(servers: Server[]) {
        servers.forEach((server,index) => {
            let componentFactory = this.componentFactoryResolver.resolveComponentFactory(
                ServerStatusComponent
            );
            let componentRef = this.xnetServerStatusTemplate.createComponent(
                componentFactory
            );
            let instanceServerStatusComponent: ServerStatusComponent = componentRef.instance as ServerStatusComponent;
            instanceServerStatusComponent.server = server;
            this.serverStatusArr.push(componentRef.instance as ServerStatusComponent);
        });
    }
}
