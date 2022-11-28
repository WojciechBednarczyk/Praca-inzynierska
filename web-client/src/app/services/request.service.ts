import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) {
  }

  sendRequest(menteeId: number, personalTrainerId: number): Observable<HttpResponse<string>> {
    const httpParams = new HttpParams().set("menteeId", menteeId).set("personalTrainerId", personalTrainerId);
    const url = 'http://localhost:8080/api/auth/personal-trainer/request';
    return this.http.post(url, '', {params: httpParams, observe: 'response', responseType: 'text'});
  }
}
