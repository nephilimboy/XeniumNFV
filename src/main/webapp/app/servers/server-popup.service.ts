import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { timeout } from 'q';
// import { GroupService } from './group.service';

@Injectable()
export class ServerPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        // private groupService: GroupService
    ) { }

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;
        if (true) {
            /* Damn Important 
             USE THIS SERVICE HERE ,NEITHER IN RESOLVER NOR POPUP-OPENER-COMPONENT ----> 
             OTHERWISE IT'LL RISE ERROR --> HERE IS PART OF ERROR :
             " ERROR Error: ExpressionChangedAfterItHasBeenCheckedError: Expression has changed 
             after it was checked. Previous value: 'undefined'. Current value: 'true'. 
             It seems like the view has been created after its parent and its children 
             have been dirty checked. Has it been created in a change detection hook ? "
             */
            // this.groupService.getActionList(id).subscribe((groupActionList) => {
                // this.serverModalRef(component, groupActionList);
            // });
            setTimeout(() => {
                console.log('Time'),
                console.log('ddd');
            console.log(component);
            this.serverModalRef(component, '1');
              }, 1000);
            
        } else {
            // console.log('#ERR: No Group Id Is Available');
            
        }
    }

    serverModalRef(component: Component, groupActionList: string): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static' });
        console.log('3333');
        // modalRef.componentInstance.groupActionList = groupActionList;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
