/*
*
*    @ AH.GHORAB
*
*/
import {BaseEntity} from '../../shared/baseEntity/baseEntity.model';
import {DiagramEntityConnection} from '../tab-diagram-drawer/diagramEntityConnection.model';

export class Switch extends BaseEntity<number> {
    constructor(
        public source?: string,
        public key?: string,
        public name?: string,
        public describtion?: string,
        public loc?: string,
        public leftArray?: string[],
        public topArray?: string[],
        public bottomArray?: string[],
        public rightArray?: string[],
        public diagramEntityConnections?: DiagramEntityConnection[]

    ) {
        super();
        this.id = -1;
        this.source = source ? source : '';
        this.key = key ? key : '';
        this.name = name ? name : '';
        this.describtion = describtion ? describtion : '';
        this.loc = loc ? loc : '';
        this.leftArray = leftArray ? leftArray : [];
        this.topArray = topArray ? topArray : [];
        this.bottomArray = bottomArray ? bottomArray : [];
        this.rightArray = rightArray ? rightArray : [];
        this.diagramEntityConnections = diagramEntityConnections ? diagramEntityConnections : new Array();
    }
}
