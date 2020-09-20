import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-clearing-member',
  templateUrl: './clearing-member.component.html',
  styleUrls: ['./clearing-member.component.scss']
})
export class ClearingMemberComponent implements OnInit {

  private participants;
  constructor(private http : HttpClient) {

    console.log("cm activated");
   }

  ngOnInit() {

    this.http.get("http://localhost:8083/Settlement_System/rest/hello/getAllParticipants").subscribe(
      data=> {
        this.participants = data;
        
        console.log("participants => " + data);
      });
  }

}
