import {Resolve} from "@angular/router";
import {Injectable} from "@angular/core";
import {TrainingPlanService} from "../services/training-plan.service";

@Injectable()
export class TrainingPlansResolver implements Resolve<any> {

  userId!: number;

  constructor(private trainingPlansSerivce: TrainingPlanService) {
  }

  resolve(): any {
    this.userId = this.getUserId()
    return this.trainingPlansSerivce.getTrainingPlans(this.userId);
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }
}
