import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {map} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.checkAuth().subscribe((res:any) => {
      if(!res) {
        console.log('has auth');
        this.router.navigate(['/login']);
      } else {
        console.log('no auth');
        this.router.navigate(['/dashboard']);
      }
    });
  }

}
