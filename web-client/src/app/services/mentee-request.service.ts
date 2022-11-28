import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MenteeRequestService {

  constructor(private http: HttpClient) {
  }

  getRequests(personalTrainerId: number) {
    const url = 'http://localhost:8080/api/auth/personal-trainer/request';
    let httpParams = new HttpParams();
    httpParams = httpParams.append("personalTrainerId", personalTrainerId);
    return this.http.get(url, {params: httpParams});
  }

}
