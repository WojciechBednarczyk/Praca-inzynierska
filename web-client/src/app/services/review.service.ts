import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  observe: 'response',
  responseType: 'text',
};

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) {
  }

  sendReview(review: string, rating: number, personalTrainerId: number, userId: number): Observable<HttpResponse<string>> {
    const url = 'http://localhost:8080/api/auth/opinion';
    return this.http.post(url, {
      review: review,
      rating: rating,
      personalTrainerId: personalTrainerId,
      userId: userId,
    }, {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response', responseType: 'text'});
  }
}
