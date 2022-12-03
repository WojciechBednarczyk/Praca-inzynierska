import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TrainingExercise} from "../training-exercise";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TrainingPlanService {

  constructor(private http: HttpClient) {
  }

  saveTrainingPlan(trainingPlan: TrainingExercise[], userId: number): Observable<any> {
    const url = 'http://localhost:8080/api/auth/training-plan';
    return this.http.post(url, {trainingPlan: trainingPlan, userId: userId}, httpOptions);
  }
}
