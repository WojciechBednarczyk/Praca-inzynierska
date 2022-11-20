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

  exercises!: Exercise[] | any

  constructor(private http: HttpClient) {
  }

  loadExercises() {
    this.http.get<Exercise[]>('http://localhost:8080/api/auth/get', httpOptions)
      .toPromise().then(data => this.exercises = data)
  }

  getExercises(): Exercise[] {
    return this.exercises;
  }

  getExerciseById(id: String): Exercise {

    let result!: Exercise
    this.exercises.forEach((element: Exercise) => {
      if (element.id.toString() === id) {
        result = element;
      }
    });
    return result;
  }
}
