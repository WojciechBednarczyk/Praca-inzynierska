import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

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

  setDescription(description: string, userId: number): Observable<any> {
    const url = 'http://localhost:8080/api/auth/personal-trainer/description';
    return this.http.post<any>(url, {
      description: description,
      userId: userId
    }, httpOptions);
  }

  updateProfile(form: any, userId: number): Observable<any> {
    const url = 'http://localhost:8080/api/auth/user/profile';
    return this.http.post<any>(url, {
      weight: form.weight,
      height: form.height,
      bodyFat: form.bodyFat,
      waistCircumference: form.waistCircumference,
      bicepsCircumference: form.bicepsCircumference,
      thighCircumference: form.thighCircumference,
      chestCircumference: form.chestCircumference,
      location: form.location,
      userId: userId
    }, httpOptions);
  }

  removeMentee(requestId: number) {
    const httpParams = new HttpParams().set("userId", requestId);
    const url = 'http://localhost:8080/api/auth/personal-trainer/mentee/remove';
    return this.http.post(url, '', {params: httpParams});
  }
}
