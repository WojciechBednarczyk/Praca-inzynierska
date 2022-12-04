import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {DateService} from '../services/date.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    firstName: null,
    secondName: null,
    login: null,
    password: null,
    role: null,
    email: null,
    dateOfBirth: null,

  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private dateService: DateService) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const {firstName, secondName, login, password, role, email, dateOfBirth} = this.form;
    const dateOfBirthFormatted = this.dateService.formatDate(dateOfBirth)
    this.authService.register(firstName, secondName, login, password, role, email, dateOfBirthFormatted).subscribe({
      next: data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }

}
