import { HttpInterceptor, EventManager } from 'ng-jhipster';
import { RequestOptionsArgs, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

// import { AlertToasterType } from '../../shared/alert/alert-toaster.enum'

export class ErrorHandlerInterceptor extends HttpInterceptor {

    constructor(private eventManager: EventManager) {
        super();
    }

    requestIntercept(options?: RequestOptionsArgs): RequestOptionsArgs {
        return options;
    }

    responseIntercept(observable: Observable<Response>): Observable<Response> {
        return <Observable<Response>>observable.catch((error) => {

            if (!(error.status === 401 && (error.text() === '' ))) {
                console.log('im eRR hndl');
                console.log(error._body)
                console.log(error.json().message);
                console.log('--------------------------------------');
                // this.eventManager.broadcast(
                //     {
                //         name: '',
                //         content: {
                //             message: error.text(),
                //             type: AlertToasterType.error
                //         }
                //     });
            }
            return Observable.throw(error);
        });
    }
}
