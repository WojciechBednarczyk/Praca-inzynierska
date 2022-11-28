import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {StorageService} from '../services/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean = false;
  role: string = ''
  isMentee: boolean = false;
  isPersonalTrainer: boolean = false


  constructor(private storageService: StorageService,
              private authService: AuthService) {
  }

  logout() {
    this.authService.logout();
    this.storageService.removeUser();
    this.isLoggedIn = false;
    this.isMentee = false;
    this.isPersonalTrainer = false;
    window.location.reload();
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    this.role = this.getRole();
    if (this.role === "ROLE_MENTEE") {
      this.isMentee = true;
      this.isPersonalTrainer = false;
    } else if (this.role === "ROLE_PERSONAL_TRAINER") {
      this.isMentee = false;
      this.isPersonalTrainer = true;
    }
  }

  private getRole() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).role : [];
  }
}
