import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-description-dialog',
  templateUrl: './description-dialog.component.html',
  styleUrls: ['./description-dialog.component.css']
})
export class DescriptionDialogComponent implements OnInit {

  form: any = {
    description: null
  };
  isDescriptionSet: boolean = false;
  userId!: number;
  description!: string

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

  setDescription() {
    this.description = this.form.description;
    this.userService.setDescription(this.description, this.userId)
      .subscribe();
    this.isDescriptionSet = true;
  }
}
