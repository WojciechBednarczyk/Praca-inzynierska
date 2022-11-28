import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MenteesRequest} from "./mentees-request";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-mentee-request',
  templateUrl: './mentee-request.component.html',
  styleUrls: ['./mentee-request.component.css']
})
export class MenteeRequestComponent implements OnInit {

  data!: any;
  menteesRequests: MenteesRequest[] = []
  displayedColumns: string[] = ['mentee', 'accept', 'reject'];
  dataSource!: MatTableDataSource<MenteesRequest>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data;
    })
    for (let i = 0; i < this.data.data.length; i++) {
      this.menteesRequests.push(this.data.data[i])
    }
    this.dataSource = new MatTableDataSource<MenteesRequest>(this.menteesRequests)
    this.dataSource.paginator = this.paginator;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
