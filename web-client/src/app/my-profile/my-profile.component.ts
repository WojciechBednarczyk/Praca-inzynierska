import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProfileRouteResolver} from '../resolvers/profile-route-resolver';
import {MatDialog} from "@angular/material/dialog";
import {DescriptionDialogComponent} from "../description-dialog/description-dialog.component";
import {EditProfileDialogComponent} from "../edit-profile-dialog/edit-profile-dialog.component";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  data: any
  isMentee: boolean = false
  isPersonalTrainer: boolean = false

  constructor(private activatedRoute: ActivatedRoute,
              private profileRouteResolver: ProfileRouteResolver,
              public dialog: MatDialog,) {

  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data
      if (this.profileRouteResolver.getRole() === 'ROLE_MENTEE') {
        this.isMentee = true;
        this.isPersonalTrainer = false;
      } else if (this.profileRouteResolver.getRole() === 'ROLE_PERSONAL_TRAINER') {
        this.isPersonalTrainer = true;
        this.isMentee = false;
      }
    })
  }

  addDescription() {
    const dialogRef = this.dialog.open(DescriptionDialogComponent, {
      width: '50%',
      height: '60%',
    });

    dialogRef.componentInstance.userId = this.getUserId();

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      window.location.reload();
    });
  }

  editProfile() {
    const dialogRef = this.dialog.open(EditProfileDialogComponent, {
      // width: '35%',
      // height: '70%',
    });

    dialogRef.componentInstance.userId = this.getUserId();
    dialogRef.componentInstance.isMentee = this.isMentee;
    dialogRef.componentInstance.isPersonalTrainer = this.isPersonalTrainer;

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      window.location.reload();
    });
  }

  getUserId() {
    const token = window.sessionStorage.getItem('auth-user');

    return token ? JSON.parse(token).id : [];
  }
}
