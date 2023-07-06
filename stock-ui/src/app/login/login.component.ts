import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {UserService} from "../service/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {Config} from "../shared/constants/config";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  user: any = {};

  constructor(
              private userService : UserService,
              private cookieService: CookieService,
              private config: Config,
              private snackBar: MatSnackBar,
              private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.user).subscribe({
        next: (data: any) => {
          this.cookieService.set(this.config.jwtTokenKey, data.jwtToken, {path: '/'});
          this.router.navigate(['/']);
        },
        error: err => this.snackBar.open('Invalid Login')
      })
  }

  logout() {
    this.cookieService.delete(this.config.jwtTokenKey);
  }
}
