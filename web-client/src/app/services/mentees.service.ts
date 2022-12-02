import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MenteesService {

  constructor(private http: HttpClient) {
  }

  getMentees(userId: number) {
    const url = 'http://localhost:8080/api/auth/personal-trainer/mentees';
    let httpParams = new HttpParams();
    httpParams = httpParams.append("userId", userId);
    return this.http.get(url, {params: httpParams});
  }
}
