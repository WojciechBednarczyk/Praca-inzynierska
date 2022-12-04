import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserProfileRouteResolver} from "../resolvers/user-profile-route-resolver";
import {MatDialog} from "@angular/material/dialog";
import {MessageDialogComponent} from "../message-dialog/message-dialog.component";
import {RequestService} from "../services/request.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ReviewDialogComponent} from "../review-dialog/review-dialog.component";
import {ReviewService} from "../services/review.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  data: any;
  isMentee: boolean = false;
  isPersonalTrainer: boolean = false;
  response: string | null = '';
  areOpinionsVisible: boolean = false;
  opinions: any;
  displayedColumns: string[] = ['review', 'rating'];
  dataSource!: MatTableDataSource<any>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute,
              private userProfileRouteResolver: UserProfileRouteResolver,
              public dialog: MatDialog,
              private requestService: RequestService,
              route: ActivatedRoute,
              private _snackBar: MatSnackBar,
              private reviewService: ReviewService,) {

  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data
      if (this.userProfileRouteResolver.getRole() === 'ROLE_MENTEE') {
        this.isMentee = true;
        this.isPersonalTrainer = false;
      } else if (this.userProfileRouteResolver.getRole() === 'ROLE_PERSONAL_TRAINER') {
        this.isPersonalTrainer = true;
        this.isMentee = false;
      }
    })
    this.reviewService.getOpinions(this.data.data.userId)
      .toPromise().then(data => {
      this.opinions = data;
      this.dataSource = new MatTableDataSource<any>(this.opinions);
      this.dataSource.paginator = this.paginator;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  openDialog() {
    const dialogRef = this.dialog.open(MessageDialogComponent, {
      width: '50%',
      height: '60%',
    });

    dialogRef.componentInstance.receiverId = this.data.data.userId;

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  async openReviewDialog() {
    const dialogRef = this.dialog.open(ReviewDialogComponent, {
      width: '50%',
      height: '70%',
    });

    dialogRef.componentInstance.personalTrainerId = this.data.data.userId;
    dialogRef.componentInstance.menteeId = this.getUserId();

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }

  sendRequest() {
    this.requestService.sendRequest(this.getUserId(), this.data.data.userId)
      .subscribe(response => {
        this.response = response.body
        if (this.response != null) {
          this._snackBar.open(this.response, "", {duration: 5000})
        }
      });

  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }

  showOpinions() {
    this.areOpinionsVisible = true;
    this.dataSource = new MatTableDataSource<any>(this.opinions)
  }
}
