import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {MatButtonModule} from '@angular/material/button';
import {LoginComponent} from './login/login.component';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MAT_DATE_LOCALE, MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {PasswordResetComponent} from './password-reset/password-reset.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {YouTubePlayerModule} from '@angular/youtube-player';
import {MatDialogModule} from '@angular/material/dialog';

import {httpInterceptorProviders} from './helpers/auth.interceptor';
import {RegisterComponent} from './register/register.component';
import {DateService} from './services/date.service';
import {AtlasComponent} from './atlas/atlas.component';
import {ExerciseDetailsComponent} from './exercise-details/exercise-details.component';
import {MyProfileComponent} from './my-profile/my-profile.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {SearchUsersComponent} from './search-users/search-users.component';
import {MessageDialogComponent} from './message-dialog/message-dialog.component';
import {MessagesComponent} from './messages/messages.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    PasswordResetComponent,
    RegisterComponent,
    AtlasComponent,
    ExerciseDetailsComponent,
    MyProfileComponent,
    UserProfileComponent,
    SearchUsersComponent,
    MessageDialogComponent,
    MessagesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRippleModule,
    MatSelectModule,
    FormsModule,
    HttpClientModule,
    MatMenuModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    YouTubePlayerModule,
    MatDialogModule,
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
    httpInterceptorProviders,
    DateService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
