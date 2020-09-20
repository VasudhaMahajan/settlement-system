import { Component, OnInit } from '@angular/core';
import { ClientSessionService } from '../services/client-session.service';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-cost-of-settlement',
  templateUrl: './cost-of-settlement.component.html',
  styleUrls: ['./cost-of-settlement.component.css']
})
export class CostOfSettlementComponent implements OnInit {
  private cId;
  private costarray;
  private costarraysec;
  constructor(private service1 : ClientSessionService , private service:ClientService) { }

  ngOnInit() {
    this.cId = this.service1.getClientId();
    var clientID = {"id":this.cId};
    this.service.costOfSettlement(clientID).subscribe(data => 
      {
        console.log("COS => " + data);
        this.costarray = data;
      });

      this.service.costOfSettlementSec(clientID).subscribe(data=>{
        this.costarraysec = data;
      });
  }

}
