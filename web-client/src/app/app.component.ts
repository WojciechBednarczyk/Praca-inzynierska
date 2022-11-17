import {Component} from '@angular/core';
import {ExerciseProviderService} from './atlas/exercise/exercise-provider.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'akademia-treningu';

  constructor(private exerciseProviderService: ExerciseProviderService) {
    exerciseProviderService.loadExercises();
  }
}
