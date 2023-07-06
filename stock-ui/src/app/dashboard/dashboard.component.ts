import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {EditWatchComponent} from "../edit-watch/edit-watch.component";
import {WatchService} from "../service/watch.service";
import {HealthService} from "../service/health.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  displayedColumns: string[] = ['stock', 'currentValue', 'watchValue',
    'watchAction', 'action'];
  watchlist:any;
  serverHealth:any = {status: null, date: null};

  constructor(public dialog: MatDialog,
              private watchService: WatchService,
              private healthService: HealthService) { }

  ngOnInit(): void {
    this.getWatchlist();

    this.healthService.getServerHealth().subscribe({
      next: (value: any) => {
        this.serverHealth = value;
      },
      error: () => {
        this.serverHealth = {status: 'BAD'};
      }
    })
  }

  getWatchlist() {
    this.watchService.getWatchlist().subscribe({
      next: (value: any) => {
        this.watchlist = value;
      }
    });
  }

  edit(stock: any) {
    this.dialog.open(EditWatchComponent, {data: stock});
  }

  delete(stock: any) {
    this.watchService.deleteWatch(stock).subscribe({
      next: () => {
        this.getWatchlist();
      }
    });
  }

  add() {
    this.dialog.open(EditWatchComponent, {data: {}});
  }

  isImportant(stock: any) {
    if (stock.action === 'BUY') {
      return stock.currentPrice <= stock.price;
    } else if (stock.action === 'SELL') {
      return stock.currentPrice >= stock.price;
    } else {
      return false;
    }
  }

}
