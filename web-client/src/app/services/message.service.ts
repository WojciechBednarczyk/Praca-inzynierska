import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Message} from "../message";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class MessageService {


  constructor(private http: HttpClient) {

  }

  postMessage(message: Message): Observable<Message> {
    const url = 'http://localhost:8080/api/auth/message';
    return this.http.post<Message>(url, message, httpOptions);
  }

  getMessages(userId: number) {
    const url = 'http://localhost:8080/api/auth/messages';
    let httpParams = new HttpParams();
    httpParams = httpParams.append("userId", userId);
    return this.http.get(url, {params: httpParams});
  }
}
