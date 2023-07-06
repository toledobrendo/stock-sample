import {Injectable} from "@angular/core";
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import {catchError, map, Observable, tap, throwError} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {Config} from "../constants/config";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService,
              private config: Config,
              private router: Router) {
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): any {
    if (req.headers.get("skip")) {
      req = req.clone({
        headers: req.headers.delete('skip')
      });
      return next.handle(req);
    }

    console.log("cookie token " + this.config.jwtTokenKey);
    console.log("cookie " + this.cookieService.get(this.config.jwtTokenKey));

    req = req.clone({
      setHeaders: {
        Authorization: "Bearer " + this.cookieService.get(this.config.jwtTokenKey)
      }
    });

    return next.handle(req).pipe(
        map((event: HttpEvent<any>) => {
          return event;
        }),
        catchError((err) : any => {
          if (err.status !== 401) {
            return throwError(() => err);
          }
          this.router.navigate(['404']);
        })
    );
  }
}
