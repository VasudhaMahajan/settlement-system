import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DialogService } from 'primeng/api';
import { ViewTransactionDetailsComponent } from '../view-transaction-details/view-transaction-details.component';
import { AddFundsComponent } from '../add-funds/add-funds.component';
import { ClientSessionService } from '../services/client-session.service';
import { ClientService } from '../services/client.service';
import { NettingDetailsComponent } from '../netting-details/netting-details.component';
import { ObligationComponent } from '../obligation/obligation.component';
import { CostOfSettlementComponent } from '../cost-of-settlement/cost-of-settlement.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientview',
  templateUrl: './clientview.component.html',
  styleUrls: ['./clientview.component.css']
})
export class ClientviewComponent implements OnInit {

  private url = 'assets/clientTrade.json';
  private clientTrades;
  private clientInfo;
  private corporateActions; 
  private securityBalance;
  private accountBal = {"bal":10000000};
  private equityBal = [
    {"secname":"APPLE" , "qty":100},
    {"secname":"FB" , "qty":111},
    {"secname":"HUL" , "qty":909},
    {"secname":"SBIN" , "qty":78},
    {"secname":"SBIN" , "qty":78},
    {"secname":"SBIN" , "qty":78},

    
   
  ];

  // [
  //   {"craction":"stock divident","secName":"apple","ratio":"1:3"},
  //   {"craction":"rights","secName":"FB","ratio":"1:5"},
  //   {"craction":"stock split","secName":"Citi","ratio":"1:1"},
  //   {"craction":"rights","secName":"apple","ratio":"1:6"},
  // ];
  private cId = 0;
  private rights = false;
  private rightsExecuted = 'EXECUTE RIGHT';

  constructor(
    private http: HttpClient,
    public dialogService: DialogService,
    private service1:ClientSessionService,
    private service:ClientService,
    private router : Router) { }

  ngOnInit() {
    // this.http.get(this.url).subscribe(
    //   response => {
    //     this.clientTrades = response;
    //      console.log(this.clientTrades);
    //   }
    // );

    this.cId = this.service1.getClientId();
    var clientID = {"id":this.cId};
    //client Info
    this.service.getClientInformation(clientID).subscribe(response => {
      console.log(response);
      this.clientInfo = response;
    });
    //transaction table
    this.service.getAllTrades(clientID).subscribe(response => {
      console.log(response);
      this.clientTrades = response;
    });

    //corporate action table
    this.service.getCorporateAction(clientID).subscribe(response => {
      console.log("corporate actions => " + response);
      this.corporateActions = response;
    });

    //getnetsecurities

    this.service.getSecurities(clientID).subscribe(response => {
      console.log("sec bal"+response);
      this.securityBalance = response;

    });
  }
  testright(right:any) {
    
   //console.log("in testright" + this.clientTrades);

      if(right.corporateActionName === 3)
      {
        this.rights = true;

        return true;
      }
      else{
        return false;
      }
  }

  executeRights(cactionInfo) {
    this.cId = this.service1.getClientId();
    console.log(cactionInfo.securityId);
   this.service.executeRights({"id":this.cId , "security_id":cactionInfo.securityId}).subscribe(
     response => {
       if(response)
       {
         //write something here
       }
       else
       {

       }
      }
   );
  }

  addFunds()
  {
    const ref = this.dialogService.open(AddFundsComponent, {
      header: 'Add Funds',
      width: '70%'
  }); 
  }
  obligation()
  {
    const ref = this.dialogService.open(ObligationComponent, {
      header: 'Add Funds',
      width: '70%'
  }); 
  }

  costSettlement()
  {
    const ref = this.dialogService.open(CostOfSettlementComponent, {
      header: 'Add Funds',
      width: '70%'
  }); 
  }

  viewDetails() {
    const ref = this.dialogService.open(ViewTransactionDetailsComponent, {
      header: 'Transaction Details',
      width: '70%'
  }); 

  

  }
  viewNettingDetails(){
    
    const ref = this.dialogService.open(NettingDetailsComponent, {
      header: 'Netting Details',
      width: '70%'
  }); 

  }

  logout()
  {
    this.http.post("http://localhost:8083/Settlement_System/rest/ParticipantFirst/logout" , {id:"1"});
    this.router.navigateByUrl("/");
  }


}
