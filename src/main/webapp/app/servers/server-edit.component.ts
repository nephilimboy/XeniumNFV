import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Response } from "@angular/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FormGroup, FormArray, FormBuilder, Validators } from "@angular/forms";

import { NgbActiveModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { EventManager } from "ng-jhipster";
import { ServerPopupService } from "./server-popup.service";
import { EventName } from "../blocks/config/eventName.enum";
import { Server } from "./server.model";
import { NetworkCard } from "./networkCard.model";
import { networkDiagramRoute } from "../networkDiagram/networkDiagram.routing";
import { ServerService } from "./server.service";

@Component({
    selector: "server-popup",
    templateUrl: "./server-edit.component.html"
})
export class ServerPopupComponent implements OnInit, OnDestroy {
    public networkCards: FormGroup;

    private newServer: Server = new Server();
    private newPrimNetworkCard: NetworkCard = new NetworkCard();

    constructor(
        public activeModal: NgbActiveModal,
        // private eventManager: EventManager,
        private formBuilder: FormBuilder,
        private serverService: ServerService
    ) {}

    ngOnInit() {
        this.networkCards = this.formBuilder.group({
            itemRows: this.formBuilder.array([this.initItemRows()])
        });
    }
    clear() {
        this.activeModal.dismiss("cancel");
    }

    save() {
        this.newPrimNetworkCard.isPrimary = true;
        console.log(this.newPrimNetworkCard)
        this.newServer.networkCards.push(this.newPrimNetworkCard);
        this.networkCards.value.itemRows.forEach(element => {
            let newNetworkCard: NetworkCard = new NetworkCard();
            newNetworkCard.isPrimary = false;
            newNetworkCard.name = element.itemname;
            this.newServer.networkCards.push(newNetworkCard);
        });
        console.log(this.newServer);
        this.serverService.save("/new",this.newServer).subscribe((res: any) =>
        {
        this.onSaveSuccess(res);
        });

    }
    private onSaveSuccess(result) {
        this.activeModal.dismiss(result);
    }

    trackUserById(index: number, item: any) {}

    initItemRows() {
        return this.formBuilder.group({
            itemname: [""]
        });
    }

    addNewRow() {
        const control = <FormArray>this.networkCards.controls["itemRows"];
        control.push(this.initItemRows());
    }

    deleteRow(index: number) {
        const control = <FormArray>this.networkCards.controls["itemRows"];
        control.removeAt(index);
    }

    ngOnDestroy() {
        // this.eventManager.broadcast({
        //     name: EventName.serverPopup,
        //     content: ''
        // });
    }
}

@Component({
    selector: "server-popup-openner",
    template: ""
})
export class ServerPopupOpennerComponent implements OnInit, OnDestroy {
    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private serverPopupService: ServerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if (params["id"]) {
                this.modalRef = this.serverPopupService.open(
                    ServerPopupComponent as Component,
                    params["id"]
                );
            } else {
                this.modalRef = this.serverPopupService.open(
                    ServerPopupComponent as Component
                );
            }
        });
    }

    ngOnDestroy() {}
}
