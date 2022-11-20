import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProfileRouteResolver} from '../resolvers/profile-route-resolver';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  data: any
  isMentee: boolean = false
  isPersonalTrainer: boolean = false

  constructor(private activatedRoute: ActivatedRoute, private profileRouteResolver: ProfileRouteResolver) {

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
}
