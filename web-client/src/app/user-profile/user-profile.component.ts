import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserProfileRouteResolver} from "../resolvers/user-profile-route-resolver";
import {MatDialog} from "@angular/material/dialog";
import {MessageDialogComponent} from "../message-dialog/message-dialog.component";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  data: any
  isMentee: boolean = false
  isPersonalTrainer: boolean = false

  constructor(private activatedRoute: ActivatedRoute,
              private userProfileRouteResolver: UserProfileRouteResolver,
              public dialog: MatDialog,
              route: ActivatedRoute) {

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
}
