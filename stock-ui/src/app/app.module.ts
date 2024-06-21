import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from "./login/login.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatTableModule} from "@angular/material/table";
import {UserService} from "./service/user.service";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./shared/interceptor/auth.interceptor";
import { HomeComponent } from './home/home.component';
import { EditWatchComponent } from './edit-watch/edit-watch.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {WatchService} from "./service/watch.service";
import {MatIconModule} from "@angular/material/icon";
import {HealthService} from "./service/health.service";
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    HomeComponent,
    EditWatchComponent,
    PrivacyPolicyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatTableModule,
    MatSnackBarModule,
    HttpClientModule,
    MatDialogModule,
    MatSelectModule,
    MatIconModule
  ],
  providers: [
    UserService,
    WatchService,
    HealthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
