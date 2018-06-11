/*
 *    @ AH.GHORAB
 */
import {ComponentRef} from '@angular/core';

export class NavigationMenuItem {
    constructor(public id?: number,
                public topic?: string,
                public url?: string,
                public icon?: string,
                public isEnable?: boolean,
                public isBookmark?: boolean,
                public isOpen?: boolean,
                public isShowing?: boolean,
                public childs?: NavigationMenuItem[]
            ){
        this.id = 0;
        this.topic = '';
        this.url = '';
        this.icon = '';
        this.isEnable = false;
        this.isBookmark = false;
        this.isOpen = false;
        this.isShowing = false;
        this.childs = [];
    }
}

