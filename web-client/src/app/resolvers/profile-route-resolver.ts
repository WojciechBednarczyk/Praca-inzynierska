import {Injectable} from "@angular/core";
import {Resolve} from "@angular/router";
import {UserService} from "../services/user.service";

@Injectable()
export class ProfileRouteResolver implements Resolve<any> {

  userId!: number;
  role!: string;

  constructor(private userService: UserService) {

  }

  resolve() {
    this.userId = this.getUserId()
    this.role = this.getRole()
    return this.userService.getUser(this.userId, this.role);
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }

  getRole() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).role : [];
  }
}
