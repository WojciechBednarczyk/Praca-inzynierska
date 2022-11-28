import {Injectable} from "@angular/core";
import {Resolve} from "@angular/router";
import {MenteeRequestService} from "../services/mentee-request.service";

@Injectable()
export class MenteeRequestResolver implements Resolve<any> {

  constructor(private menteeRequestService: MenteeRequestService) {
  }

  resolve(): any {
    return this.menteeRequestService.getRequests(this.getUserId());
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }
}
