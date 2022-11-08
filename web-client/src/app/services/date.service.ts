import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() {
  }

  formatDate(date: string | number | Date): string {
    const _date = new Date(date);
    const day = _date.getDate();
    const month = _date.getMonth() + 1;
    const year = _date.getFullYear();
    return `${year}/${month}/${day}`;
  }
}
