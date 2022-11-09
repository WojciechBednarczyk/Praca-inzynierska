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


  constructor(private storageService: StorageService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.storageService.removeUser();
    window.location.reload();
  }
}
