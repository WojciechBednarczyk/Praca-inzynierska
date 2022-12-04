import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {MessageWithSender} from "../messages/message-with-sender";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-training-plans',
  templateUrl: './training-plans.component.html',
  styleUrls: ['./training-plans.component.css']
})
export class TrainingPlansComponent implements OnInit {

  data!: any;
  displayedColumns: string[] = ['date of create'];
  displayedColumns2: string[] = ['name', 'muscle group', 'sets', 'reps'];
  dataSource!: MatTableDataSource<MessageWithSender>
  dataSource2!: MatTableDataSource<MessageWithSender>

  @ViewChild('paginatorLeft') paginator!: MatPaginator;

  @ViewChild('paginatorRight') paginator2!: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute,
              private route: Router) {

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource2.paginator = this.paginator2;
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data;
    })
    this.dataSource = new MatTableDataSource<MessageWithSender>(this.data.data)
    this.dataSource2 = new MatTableDataSource<MessageWithSender>()
  }

  showTrainingPlan(trainingPlan: any) {
    const index = this.data.data.indexOf(trainingPlan, 0);
    this.dataSource2 = this.data.data[index].trainingPlan;
  }

  goToExerciseDetails(trainingPlan: any) {
    this.route.navigateByUrl("/atlas/" + trainingPlan.exerciseId)
  }
}
