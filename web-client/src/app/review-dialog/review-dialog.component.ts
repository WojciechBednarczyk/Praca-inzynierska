import {Component, OnInit} from '@angular/core';
import {ReviewService} from "../services/review.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.css']
})
export class ReviewDialogComponent implements OnInit {

  personalTrainerId!: number;
  menteeId!: number;
  review!: string
  isReviewSent: boolean = false;
  rating!: number;
  response: string | null = ''

  form: any = {
    review: null,
    rating: null,
  };

  constructor(private reviewService: ReviewService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  async sendReview() {
    this.review = this.form.review;
    this.rating = this.form.rating;
    await this.reviewService.sendReview(this.review, this.rating, this.personalTrainerId, this.menteeId)
      .subscribe(response => {
        this.response = response.body
        if (this.response != null) {
          this._snackBar.open(this.response, "", {duration: 5000})
        }
      });
  }

  getResponse(): string | null {
    return this.response;
  }
}
