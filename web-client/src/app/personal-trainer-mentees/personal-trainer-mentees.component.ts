import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MenteesRequest} from "../mentee-request/mentees-request";
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-personal-trainer-mentees',
  templateUrl: './personal-trainer-mentees.component.html',
  styleUrls: ['./personal-trainer-mentees.component.css']
})
export class PersonalTrainerMenteesComponent implements OnInit {

  data!: any;
  displayedColumns: string[] = ['mentee', 'dateOfBirth', 'location', 'create-training-plan', 'profile', 'remove'];
  dataSource!: MatTableDataSource<MenteesRequest>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data;
    })
    this.dataSource = new MatTableDataSource<MenteesRequest>(this.data.data)
    this.dataSource.paginator = this.paginator;
  }

  navigateToMenteeProfile(userId: number) {
    this.router.navigateByUrl('/profile/' + userId + '/ROLE_MENTEE');
  }

  removeMentee(userId: number) {
    this.userService.removeMentee(userId).subscribe();
    window.location.reload();
  }
}
