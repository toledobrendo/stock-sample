import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class Config {
  get apiBaseUrl() {
    return "http://localhost:8080"
  }

  get jwtTokenKey() {
    return "jwtToken";
  }
}
