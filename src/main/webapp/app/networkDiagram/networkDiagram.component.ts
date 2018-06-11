import {AfterViewInit, Component, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {TabsComponent} from './tabs-diagram/tabs-diagram.component';
import {Server} from '../servers/server.model';
import {ActivatedRoute} from '@angular/router';
import {ParseLinks} from 'ng-jhipster';
import {ServerService} from '../servers/server.service';
import {ResponseWrapper} from '../shared/model/response-wrapper.model';

import {DiagramOverView} from './diagram-overView/diagram-overView.component';
import {TabDiagramDrawer} from './tab-diagram-drawer/tab-diagram-drawer.component';

@Component({
    selector: "app-networkDiagram",
    templateUrl: "./networkDiagram.component.html"
})
export class NetworkDiagramComponent implements OnInit, AfterViewInit {
    public state: any = {
        tabs: {
            demo3: "hr1"
        }
    };

    @ViewChild("tabDiagramDrawer") tabDiagramDrawerTemplate;

    @ViewChildren("tabDiagramDrawer") tabDiagramDrawers: QueryList<TabDiagramDrawer>;

    @ViewChild("diagramOverView") networkDiagramOverViewRef: DiagramOverView;
    @ViewChild(TabsComponent) tabsComponent;

    public servers: Server[];
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

    ngOnInit() {}

    ngAfterViewInit() {
        // this.createDiagramDrawer('ubuntu server1');
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
        // this.createServerStatusWidget(this.servers);
        this.createDiagramDrawer(this.servers);
    }

    private onError(error) {}

    createDiagramDrawer(servers: Server[]) {
        servers.forEach(server => {
            this.tabsComponent.openTab(
                server.name,
                this.tabDiagramDrawerTemplate,
                server,
                false
            );
        });
    }

    test(){
        // console.log(this.tabDiagramDrawers);
        // this.tabDiagramDrawers.for
        // .saveTest();
        // this.networkDiagramOverViewRef.save();
    }
}
