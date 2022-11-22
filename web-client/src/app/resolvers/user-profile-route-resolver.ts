import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {UserService} from "../services/user.service";

@Injectable()
export class UserProfileRouteResolver implements Resolve<any> {

  constructor(private userService: UserService) {

  }

  resolve(route: ActivatedRouteSnapshot) {
    console.log("hello")
    return this.userService.getUser(route.params['id'], route.params['role']);
  }

}
