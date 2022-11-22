import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';

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
      return this.http.get(urlMentee, {params: httpParams});
    } else if (role === "ROLE_PERSONAL_TRAINER") {
      return this.http.get(urlPersonalTrainer, {params: httpParams});
    } else {
      return
    }
  }

  searchUsers(firstName: string, secondName: string, location: string) {
    const searchUrl = 'http://localhost:8080/api/auth/get/search';
    let httpParams = new HttpParams();
    httpParams = httpParams.append("firstName", firstName);
    httpParams = httpParams.append("secondName", secondName);
    httpParams = httpParams.append("location", location);

    return this.http.get(searchUrl, {params: httpParams});
  }
}
