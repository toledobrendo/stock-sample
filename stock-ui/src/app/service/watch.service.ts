import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class WatchService {
  constructor(private http: HttpClient) {
  }

  saveWatch(watch: any) {
    return this.http.post(environment.apiUrl + '/watch', watch);
  }

  deleteWatch(watch: any) {
    return this.http.post(environment.apiUrl + '/watch/delete', watch);
  }

  getWatchlist() {
    return this.http.get(environment.apiUrl + '/watch');
  }
}
