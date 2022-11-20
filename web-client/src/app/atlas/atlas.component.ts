import {HttpClient} from '@angular/common/http';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {Exercise} from './exercise/exercise';
import {ExerciseProviderService} from './exercise/exercise-provider.service';

@Component({
  selector: 'app-atlas',
  templateUrl: './atlas.component.html',
  styleUrls: ['./atlas.component.css']
})
export class AtlasComponent implements OnInit {

  exercises: Exercise[] | any = [];
  data: any;
  displayedColumns: string[] = ['position', 'name', 'muscle group', 'rating'];
  dataSource!: MatTableDataSource<Exercise>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(private exerciseProviderService: ExerciseProviderService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.exercises = this.exerciseProviderService.getExercises()
    this.dataSource = new MatTableDataSource<Exercise>(this.exercises)
    this.dataSource.paginator = this.paginator;
  }
}
