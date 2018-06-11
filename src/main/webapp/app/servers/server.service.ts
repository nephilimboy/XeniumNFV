/*
*
*    @ AH.GHORAB
*
*/
import { Injectable } from "@angular/core";
import { Http, Response, URLSearchParams } from "@angular/http";
import { Observable } from "rxjs/Rx";

import { Server } from "./server.model";
import { GenericService } from "../shared/genericService/genericService";
import { ResponseWrapper } from "../shared/model/response-wrapper.model";
import { createRequestOption } from "../shared/model/request-util";

@Injectable()
export class ServerService extends GenericService<Server>{
    constructor(http: Http) {
        super(http, "/api/server");
    }
}
