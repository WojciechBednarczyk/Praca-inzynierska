import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from '../my-profile/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }

  getUser(id: number, role: string) {
    const urlMentee = 'http://localhost:8080/api/auth/get/mentee';
    const urlPersonalTrainer = 'http://localhost:8080/api/auth/get/personal-trainer';
    let httpParams = new HttpParams();
    httpParams = httpParams.append("id", id);
    if (role === "ROLE_MENTEE") {
      return this.http.get<User>(urlMentee, {params: httpParams});
    } else if (role === "ROLE_PERSONAL_TRAINER") {
      return this.http.get<User>(urlPersonalTrainer, {params: httpParams});
    } else {
      return
    }
  }
}
