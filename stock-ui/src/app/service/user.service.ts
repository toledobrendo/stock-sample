import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map, Observable, of} from "rxjs";

@Injectable()
export class UserService {

  private isAuth: any = null;

  constructor(private http: HttpClient) {
  }

  login(user: any) {
    return this.http.post(environment.apiUrl + '/authenticate', user, {headers:{skip:"true"}})
      .pipe(map((data: any) => {
        this.isAuth = data.jwtToken;
        return data;
      }));
  }

  checkAuth() : Observable<any> {
    console.log("inside checkAuth");
    if (this.isAuth == null) {
      console.log("isAuth: " + this.isAuth);
      console.log("api: " + environment.apiUrl + '/check-auth');
      return this.http.get(environment.apiUrl + '/check-auth').pipe(map((res: any) => {
        this.isAuth = res;
        return res;
      }));
    } else {
      console.log("isAuth: " + this.isAuth);
      return of(this.isAuth);
    }
  }
}
