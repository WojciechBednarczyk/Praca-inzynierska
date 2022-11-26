import {Component, OnInit} from '@angular/core';
import {ProfileRouteResolver} from "../resolvers/profile-route-resolver";
import {Message} from "../message";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../services/message.service";

@Component({
  selector: 'app-message-dialog',
  templateUrl: './message-dialog.component.html',
  styleUrls: ['./message-dialog.component.css']
})
export class MessageDialogComponent implements OnInit {

  receiverId!: number
  senderId!: number
  message!: string
  isMessageSent: boolean = false

  form: any = {
    message: null
  };

  constructor(private http: HttpClient,
              private profileRouteResolver: ProfileRouteResolver,
              private messageService: MessageService,) {
  }

  ngOnInit(): void {
    this.senderId = this.profileRouteResolver.getUserId();
  }

  sendMessage() {
    this.message = this.form.message
    const message: Message = {
      senderId: this.senderId,
      receiverId: this.receiverId,
      message: this.message,
      dateOfSent: ''
    };
    this.messageService.postMessage(message)
      .subscribe();
    this.isMessageSent = true;
  }
}
