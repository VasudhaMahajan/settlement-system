import { Component, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { ClientService } from '../services/client.service';
import { ClientSessionService } from '../services/client-session.service';
@Component({
  selector: 'app-add-funds',
  templateUrl: './add-funds.component.html',
  styleUrls: ['./add-funds.component.css']
})
export class AddFundsComponent implements OnInit {

  private fund:number;
  private cId;

  constructor(private http : HttpClient , private service : ClientService , private service1 : ClientSessionService) { }

  ngOnInit() {
    this.cId = this.service1.getClientId();
    var clientID = {"id":this.cId};
  }

  addFunds()
  {
    this.service.addMoreFunds({"funds":this.fund,"id":this.cId}).subscribe(response => {
      if(response)
      {
        console.log(response);
      }
    });
   

    
  }

}
