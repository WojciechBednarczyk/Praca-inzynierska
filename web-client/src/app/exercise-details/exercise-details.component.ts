import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/internal/Subscription';
import {Exercise} from '../atlas/exercise/exercise';
import {ExerciseProviderService} from '../atlas/exercise/exercise-provider.service';

@Component({
  selector: 'app-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.css']
})
export class ExerciseDetailsComponent implements OnInit {

  exercise!: Exercise

  private routeSub!: Subscription;

  constructor(private route: ActivatedRoute, private exerciseProvider: ExerciseProviderService) {
  }

  ngOnInit(): void {
    const tag = document.createElement('script');
    tag.src = 'https://www.youtube.com/iframe_api';
    document.body.appendChild(tag);

    this.routeSub = this.route.params.subscribe(params => {
      this.exercise = this.exerciseProvider.getExerciseById(params['id'])
    });
  }

}
