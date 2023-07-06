import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {WatchService} from "../service/watch.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-edit-watch',
  templateUrl: './edit-watch.component.html',
  styleUrls: ['./edit-watch.component.scss']
})
export class EditWatchComponent {
  public watch: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<EditWatchComponent>,
              private watchService: WatchService,
              private snackBar: MatSnackBar) {
    this.watch = data;
  }

  save() {
    this.watchService.saveWatch(this.watch).subscribe({
      next: () => {
        this.snackBar.open("Watchlist saved");
        this.dialogRef.close();
      },
      error: () => {
        this.snackBar.open("Saving error");
        this.dialogRef.close();
      }
    })
  }

  close() {
    this.dialogRef.close();
  }

}
