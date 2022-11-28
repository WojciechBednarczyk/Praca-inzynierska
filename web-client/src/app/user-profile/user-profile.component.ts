import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserProfileRouteResolver} from "../resolvers/user-profile-route-resolver";
import {MatDialog} from "@angular/material/dialog";
import {MessageDialogComponent} from "../message-dialog/message-dialog.component";
import {RequestService} from "../services/request.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  data: any
  isMentee: boolean = false
  isPersonalTrainer: boolean = false
  response: string | null = ''

  constructor(private activatedRoute: ActivatedRoute,
              private userProfileRouteResolver: UserProfileRouteResolver,
              public dialog: MatDialog,
              private requestService: RequestService,
              route: ActivatedRoute,
              private _snackBar: MatSnackBar) {

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
  }

  openDialog() {
    const dialogRef = this.dialog.open(MessageDialogComponent, {
      width: '50%',
      height: '50%',
    });

    dialogRef.componentInstance.receiverId = this.data.data.userId;

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
}
