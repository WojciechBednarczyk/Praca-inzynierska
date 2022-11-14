import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {Exercise} from './exercise/exercise';
import {ExerciseProviderService} from './exercise/exercise-provider.service';

@Component({
  selector: 'app-atlas',
  templateUrl: './atlas.component.html',
  styleUrls: ['./atlas.component.css']
})
export class AtlasComponent implements OnInit {

  exercises: Exercise[] = [];
  data: any;
  displayedColumns: string[] = ['position', 'name', 'muscle group', 'rating'];

  constructor(private exerciseProviderService: ExerciseProviderService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.exerciseProviderService.getExercises()
      .subscribe(data => this.exercises = data)
  }
}
