import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrls: ['./edit-profile-dialog.component.css']
})
export class EditProfileDialogComponent implements OnInit {

  userId!: number;
  isMentee: boolean = false;
  isPersonalTrainer: boolean = false;
  isProfileUpdated: boolean = false;

  form: any = {
    weight: null,
    height: null,
    bodyFat: null,
    waistCircumference: null,
    bicepsCircumference: null,
    thighCircumference: null,
    chestCircumference: null,
    location: null,
  };

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

  editProfile() {
    this.userService.updateProfile(this.form, this.userId)
      .subscribe();
  }
}
