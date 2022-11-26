import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MessageWithSender} from "./message-with-sender";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {Message} from "../message";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, AfterViewInit {

  data!: any;
  message!: MessageWithSender;
  messages: MessageWithSender[] = [];
  messageText: string = ''
  displayedColumns: string[] = ['sender', 'date of sent'];
  dataSource!: MatTableDataSource<MessageWithSender>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.data = data;
    })
    for (let i = 0; i < this.data.data.length; i++) {
      this.messages.push(this.data.data[i])
    }
    this.dataSource = new MatTableDataSource<MessageWithSender>(this.messages)
    this.dataSource.paginator = this.paginator;
  }

  onClick(messageWithSender: Message) {
    this.messageText = messageWithSender.message;
    // console.log(messageWithSender.message);
  }
}
