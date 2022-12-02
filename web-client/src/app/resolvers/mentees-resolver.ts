import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {MenteesService} from "../services/mentees.service";

@Injectable()
export class MenteesResolver implements Resolve<any> {

  constructor(private menteesService: MenteesService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
    return this.menteesService.getMentees(this.getUserId());
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }
}
