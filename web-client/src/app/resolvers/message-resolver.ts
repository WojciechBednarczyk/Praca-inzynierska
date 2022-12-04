import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {MessageService} from "../services/message.service";

@Injectable()
export class MessageResolver implements Resolve<any> {

  constructor(private messageService: MessageService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
    return this.messageService.getMessages(this.getUserId());
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }
}
