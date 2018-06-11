/*
*
*    @ AH.GHORAB
*
*/
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {GenericService} from '../shared/genericService/genericService';
import {Topology} from './tab-diagram-drawer/topology.model';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class NetworkDiagramService extends GenericService<Topology>{
    constructor(http: Http) {
        super(http, "/api/networkDiagram");
    }

    removeDiagram(restOfUrl:string, id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl + restOfUrl}/${id}`);
    }
}
