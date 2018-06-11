/*
*
*    @ AH.GHORAB
*
*/
import { Injectable } from "@angular/core";
import { Http, Response, URLSearchParams } from "@angular/http";
import { Observable } from "rxjs/Rx";
import { createRequestOption } from "../model/request-util";
import { ResponseWrapper } from "../model/response-wrapper.model";

export abstract class GenericService<T> {
    protected resourceUrl;
    protected http: Http;

    protected constructor(http: Http, url: string) {
        this.resourceUrl = url;
        this.http = http;
    }
    save(restOfUrl:string, entity: T) {
        const copy: T = Object.assign({}, entity);
        return this.http
            .post(`${this.resourceUrl + restOfUrl}`, copy)
            .map((res: Response) => {
                return res.json();
            });
    }

    create(restOfUrl:string, entity: T): Observable<Response> {
        return this.http.post(this.resourceUrl + restOfUrl, entity);
    }

    update(restOfUrl:string, entity: T): Observable<Response> {
        return this.http.put(this.resourceUrl + restOfUrl, entity);
    }

    find(restOfUrl:string, id: number): Observable<T> {
        return this.http
            .get(`${this.resourceUrl + restOfUrl}/${id}`)
            .map((res: Response) => res.json());
    }

    query(restOfUrl:string, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http
            .get(this.resourceUrl + restOfUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(restOfUrl:string, id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl + restOfUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse);
    }
    
}
