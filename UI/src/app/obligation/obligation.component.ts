import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientService } from '../services/client.service';
import { ClientSessionService } from '../services/client-session.service';

@Component({
  selector: 'app-obligation',
  templateUrl: './obligation.component.html',
  styleUrls: ['./obligation.component.css']
})
export class ObligationComponent implements OnInit {
  private cId;
  private obligationFundData;
  private obligationShareData;

  constructor(private http : HttpClient , private service : ClientService , private service1 : ClientSessionService) { }

  ngOnInit() {

    this.cId = this.service1.getClientId();
    var clientID = {"id":this.cId};
    
    this.service.viewFundObligationReport(clientID).subscribe(data => {
      this.obligationFundData = data;
      console.log(data);
    });
   
    this.service.viewShareObligation(clientID).subscribe(data => {
      this.obligationShareData = data;
      console.log(data);
    })


    
  }

}
