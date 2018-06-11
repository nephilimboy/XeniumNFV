import { HttpInterceptor, EventManager }    from 'ng-jhipster';
import { RequestOptionsArgs, Response }     from '@angular/http';
import { Observable }                       from 'rxjs/Observable';


export class PaceHandlerInterceptor extends HttpInterceptor {

    public pendingRequests: number = 0;
    public showLoading: boolean = false;

    constructor(private eventManager: EventManager) {
        super();
    }

    requestIntercept(options?: RequestOptionsArgs): RequestOptionsArgs {
        return options;
    }

    responseIntercept(observable: Observable<Response>): Observable<Response> {
        return <Observable<Response>> observable
        .do((res: Response) => {
                console.log("Response: " + res);
                this.turnOnLoader();
            }, (err: any) => {
                this.turnOffLoader();
                console.log("Caught error: " + err);
          })
      .finally(() => {
            console.log("Finally.. delaying, though.")
            var timer = Observable.timer(500);
            timer.subscribe(t => {
              this.turnOffLoader();
            });
          });
    }
    
    private turnOnLoader() {
        console.log('new req');
        this.pendingRequests++;
        if (!this.showLoading) {
            this.showLoading = true;
            this.eventManager.broadcast( 
                {name: 'pace',
                content: {
                    numberOfPendingRequests: this.pendingRequests
                }
            });
        }
        this.showLoading = true;
  }
  
  private turnOffLoader() {
        this.pendingRequests--;
        if(this.pendingRequests <= 0) this.pendingRequests = 0;
        this.eventManager.broadcast( 
                {name: 'pace',
                content: {
                    numberOfPendingRequests: this.pendingRequests
                }
            });
    }

}
