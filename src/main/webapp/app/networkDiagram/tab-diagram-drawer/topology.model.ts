/*
*
*    @ AH.GHORAB
*
*/
import {BaseEntity} from '../../shared/baseEntity/baseEntity.model';
import {Controller} from '../accessory-controller/controller.model';
import {Switch} from '../accessory-switch/swicth.model';
import {Vnf} from '../accessory-vnfs/vnf.model';
import {DiagramEntityConnection} from './diagramEntityConnection.model';

export class Topology extends BaseEntity<number> {
    constructor(public controllers?: Controller[],
                public switches?: Switch[],
                public vnfs?: Vnf[],
                public diagramEntityConnections?: DiagramEntityConnection[]) {
        super();
        this.id = -1;
        this.controllers = controllers ? controllers : [];
        this.switches = switches ? switches : [];
        this.vnfs = vnfs ? vnfs : [];
        this.diagramEntityConnections = diagramEntityConnections ? diagramEntityConnections : [];
    }
}
