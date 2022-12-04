import {Component, OnInit} from '@angular/core';
import {Exercise} from "../atlas/exercise/exercise";

@Component({
  selector: 'app-exercise-dialog',
  templateUrl: './exercise-dialog.component.html',
  styleUrls: ['./exercise-dialog.component.css']
})
export class ExerciseDialogComponent implements OnInit {

  constructor() {
  }

  exercise!: Exercise;

  form: any = {
    sets: null,
    reps: null,
  };

  ngOnInit(): void {

  }

  saveToPlan() {
    this.exercise.sets = this.form.sets;
    this.exercise.reps = this.form.reps;
  }
}
