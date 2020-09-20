import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { ClientSessionService } from '../services/client-session.service';

@Component({
  selector: 'app-netting-details',
  templateUrl: './netting-details.component.html',
  styleUrls: ['./netting-details.component.css']
})
export class NettingDetailsComponent implements OnInit {

  cId = 0;
  private funds;
  private shares;
  constructor(private service:ClientService,private service1:ClientSessionService) { }

  ngOnInit() {

    this.cId = this.service1.getClientId();
    var clientID = {"id":this.cId};
    this.service.getNettingDetailsFunds(clientID).subscribe(response=>{
      console.log("fund: " + response);
      this.funds = response;
    });

    this.service.getNettingDetailsSecurity(clientID).subscribe(response=>{
      console.log("shares: " +response[0].securityName);
      this.shares = response;
    });
  }

}
