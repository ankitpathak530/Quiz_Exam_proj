import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private login:LoginService){ }

    intercept(req: HttpRequest<any>,
             next: HttpHandler
             ):Observable<HttpEvent<any>> {      
        //Add athe jwt token (localStorage) request
        let authReq = req;
        const token = this.login.getToken();

        if(token != null){
             authReq = authReq.clone({
                 setHeaders: { Authorization: `Bearer ${token}` },
            });
        }
        console.log("This is auth request-->",authReq);
        return next.handle(authReq);
    }
}

export const authInterceptorProviders = [
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true,
    }
]

