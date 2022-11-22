import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {UserService} from '../services/user.service';
import {User} from '../user';
import {MatPaginator} from "@angular/material/paginator";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent {

  displayedColumns: string[] = ['firstName', 'secondName', 'location', 'role'];
  form: any = {
    firstName: null,
    secondName: null,
    location: null
  };
  dataSource: any

  users: User[] | any = [];

  areUsersFound: boolean = true;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private userService: UserService, private router: Router) {
  }

  onSubmit(): void {
    const {firstName, secondName, location} = this.form;
    this.userService.searchUsers(firstName, secondName, location).subscribe({
      next: data => {
        this.users = data
        this.dataSource = new MatTableDataSource(this.users);
        this.dataSource.paginator = this.paginator;
        if (this.dataSource.length === 0) {
          this.areUsersFound = false;
        } else if (this.dataSource != 0) {
          this.areUsersFound = true;
        }
      }
    })

  }

  routeToUserProfile(id: number, role: string) {
    if (role === 'Podopieczny') {
      const url = '/profile/' + id + '/ROLE_MENTEE'
      this.router.navigate([url]);
    } else if (role === 'Trener') {
      const url = '/profile/' + id + '/ROLE_PERSONAL_TRAINER'
      this.router.navigate([url]);
    }
  }
}
