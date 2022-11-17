import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Exercise} from './exercise';

const exercise_url = 'http://localhost:8080/api/auth/get';
const httpOptions = {
  headers: new HttpHeaders()
    .set('access-control-allow-origin', "*")
    .set('Content-Type', 'application/json')
};

@Injectable({
  providedIn: 'root'
})
export class ExerciseProviderService {

  exercise!: Exercise[] | any

  constructor(private http: HttpClient) {
  }

  loadExercises() {
    this.http.get<Exercise[]>('http://localhost:8080/api/auth/get', httpOptions)
      .toPromise().then(data => this.exercise = data)
  }

  getExercises(): Exercise[] {
    return this.exercise;
  }
}
