import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(login: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        login,
        password,
      },
      httpOptions
    );
  }

  register(firstName: string, secondName: string, login: string, password: string, role: string, email: string, dateOfBirth: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        firstName,
        secondName,
        login,
        password,
        role,
        email,
        dateOfBirth,
      },
      httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {}, httpOptions);
  }
}
