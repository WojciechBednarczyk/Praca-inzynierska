import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {Exercise} from "../atlas/exercise/exercise";
import {MatTableDataSource} from "@angular/material/table";
import {ExerciseProviderService} from "../atlas/exercise/exercise-provider.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog} from "@angular/material/dialog";
import {ExerciseDialogComponent} from "../exercise-dialog/exercise-dialog.component";
import {TrainingExercise} from "../training-exercise";
import {TrainingPlanService} from "../services/training-plan.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-plan-creator',
  templateUrl: './plan-creator.component.html',
  styleUrls: ['./plan-creator.component.css']
})
export class PlanCreatorComponent implements OnInit {

  mentee: any;
  exercises: Exercise[] | any = [];
  choosenExercises: Exercise[] | any = [];
  displayedColumns: string[] = ['name', 'muscle group', 'rating', 'button'];
  displayedColumnsRight: string[] = ['name', 'muscle group', 'sets', 'reps', 'button'];
  dataSource!: MatTableDataSource<Exercise>
  choosenExercisesData!: MatTableDataSource<Exercise>
  trainingPlan: TrainingExercise[] = []

  @ViewChild('paginatorLeft') paginator!: MatPaginator;

  @ViewChild('paginatorRight') choosenPaginator!: MatPaginator;

  constructor(private route: Router,
              private exerciseProviderService: ExerciseProviderService,
              public dialog: MatDialog,
              private trainingPlanService: TrainingPlanService,
              private _snackBar: MatSnackBar) {
    this.mentee = this.route.getCurrentNavigation()?.extras.state;
  }

  ngOnInit() {
    this.exercises = JSON.parse(JSON.stringify(this.exerciseProviderService.getExercises()));
    this.dataSource = new MatTableDataSource<Exercise>(this.exercises)
    this.choosenExercisesData = new MatTableDataSource<Exercise>()
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.choosenExercisesData.paginator = this.choosenPaginator;
  }

  async addExercise(exercise: Exercise) {
    await this.openDialog(exercise);
    this.choosenExercises.push(exercise);
    const index = this.exercises.indexOf(exercise, 0);
    if (index > -1) {
      this.exercises.splice(index, 1);
    }
    this.dataSource.data = this.exercises;
    this.choosenExercisesData.data = this.choosenExercises;
  }

  remove(exercise: Exercise) {
    this.exercises.push(exercise);
    const index = this.choosenExercises.indexOf(exercise, 0);
    if (index > -1) {
      this.choosenExercises.splice(index, 1);
    }
    this.dataSource.data = this.exercises;
    this.choosenExercisesData.data = this.choosenExercises;
  }

  async openDialog(exercise: Exercise) {
    const dialogRef = this.dialog.open(ExerciseDialogComponent, {
      width: '250px',
    });
    dialogRef.componentInstance.exercise = exercise;
    await dialogRef.afterClosed().toPromise();
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  async saveTrainingPlan() {
    for (let i = 0; i < this.choosenExercises.length; i++) {
      const exercise = {
        exerciseId: this.choosenExercises[i].id,
        sets: this.choosenExercises[i].sets,
        reps: this.choosenExercises[i].reps
      };
      this.trainingPlan.push(exercise);
    }
    await this.trainingPlanService.saveTrainingPlan(this.trainingPlan, this.mentee.id).subscribe();
    this.route.navigateByUrl("/mentees").then((navigated: boolean) => {
        if (navigated) {
          this._snackBar.open("Plan treningowy został wysłany do podopiecznego", "", {duration: 5000});
        }
      }
    );
  }
}
