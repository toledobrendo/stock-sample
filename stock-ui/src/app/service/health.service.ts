import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class HealthService {
  constructor(private http: HttpClient) {
  }

  getServerHealth() {
    return this.http.get(environment.apiUrl + '/health');
  }
}
