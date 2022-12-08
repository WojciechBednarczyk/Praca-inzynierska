import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {UserService} from "../services/user.service";

@Injectable()
export class UserProfileRouteResolver implements Resolve<any> {

  role!: string

  constructor(private userService: UserService) {

  }

  resolve(route: ActivatedRouteSnapshot) {
    this.role = route.params['role']
    return this.userService.getUser(route.params['id'], route.params['role']);
  }

  getRole() {
    return this.role;
  }

  getLoggedInRole() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).role : [];
  }
}
