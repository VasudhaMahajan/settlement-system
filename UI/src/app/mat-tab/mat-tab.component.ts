import { Component, OnInit, Injectable } from '@angular/core';
import { SelectItem } from '../SelectItem';
import{Trade} from '../trade';
import {TradeService} from '../../service/tradeService';
import {Message} from 'primeng//api';
import {DialogService,MessageService} from 'primeng/api';
import {AddTransactionFormComponent} from '../add-transaction-form/add-transaction-form.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-mat-tab',
  templateUrl: './mat-tab.component.html',
  styleUrls: ['./mat-tab.component.scss'],
  providers: [DialogService,MessageService  ]
})
export class MatTabComponent implements OnInit {

  trade;
  deletedrow=false;
  trade1;
  cols: any[];
  cols2: any[];
  disable:boolean=true;
  disable1:boolean=true;
  disable2:boolean=true;
  clonedClientTrades: { [s: string]: any; } = {};
  clientTrades2 : any[];
  currentQty : any;
  nettedFunds;
  nettedShares;
  nettingResult;
  participants;
  cols1=[
    {field:'share', header:"Stock Name"},
    {field:'Qty', header:"Quantity "},
   
  ];
  showClient:boolean=false;
  percentage=0;
  corporateAction;
constructor(private tradeService:TradeService,public dialogService: DialogService,private messageService : MessageService,
  private http:HttpClient,
  
  ) { }

ngOnInit() {
  //this.tradeService.getTransactionData().then(trade => this.trade = trade);
  this.tradeService.getTransactionJSON().subscribe(trade => {
    this.trade = trade;
    console.log(trade);
  });
  this.http.get("http://localhost:8083/Settlement_System/rest/hello/getAllCorporateActions").subscribe(
    data=> {
      this.corporateAction = data;
      console.log(data);
    });

  this.cols = [
      { field: 'transactionid', header: 'Trade ID' },
      { field: 'securityName', header: 'Security' },
      { field: 'securityQuantity', header: 'Quantity' },
      { field: 'securityRate', header: 'Price' },
      { field: 'buyerName', header: 'Buyer Clearing Member' },
      { field: 'sellerName', header: 'Seller Clearing Member' }

  ];
  this.cols2 = [

    { field: 'CorporateAction', header: 'Corporate Action' },
    { field: 'SecurityName', header: 'Security Name' },
    { field: 'CorporateRatio', header: 'Corporate Action Ratio' },
    
];
  }
  showDashboard()
  {
    this.showClient = false;
  }
  generateCSR()
  {
    this.percentage = 100;
    this.disable2 = false;
    this.http.get("http://localhost:8083/Settlement_System/rest/hello/costOfSettlementReport").subscribe(data=>{
      console.log(data);
    })
  }
  AddTransaction()
  {
    const ref = this.dialogService.open(AddTransactionFormComponent, {
      header: 'Fill the transaction form',
      width: '25%',
      // contentStyle: {"max-height": "350px", "overflow": "auto"}
  });

  ref.onClose.subscribe((data) =>{

    console.log(data.transactionid);
   
    data.transactionid= this.trade[this.trade.length-1].transactionid + 1;
    this.trade.push(data);
  });
}
doNetting()
{
  this.disable = false;
  this.percentage = 50;
    this.tradeService.callNettingResult().subscribe(data=>
      {
        this.nettingResult = data;
        console.log(data);
      });  
  
}
doSettlement()
{
  this.disable1 = false;
  this.percentage = 75;
  this.tradeService.performSettelement().subscribe(data=>{
    console.log(data);
  })
}
onRowEditInit(client) {
  this.clonedClientTrades = {...client};
  console.log(this.clonedClientTrades);
  //this.editing = true;
}
onRowEditSave(client:any,index: number) {
  console.log(client);
  
  if ((client.securityQuantity) > 0) {
    delete this.clonedClientTrades[client.securityQuantity];
    this.messageService.add({severity:'success', summary: 'Success', detail:'Trade is updated'});
    this.http.post("http://localhost:8083/Settlement_System/rest/hello/updateTransaction",client).subscribe(data=>{
      console.log(data);
    });
     
  }
  else {
    //console.log("here in else");
      this.messageService.add({severity:'error', summary: 'Error', detail:'Quantity cannot be negative'});
      this.trade[index].securityQuantity = this.clonedClientTrades.securityQuantity;
  }
}
showClientList()
{
  this.http.get("http://localhost:8083/Settlement_System/rest/hello/getAllParticipants").subscribe(
    data=> {
      this.participants = data;
      
      console.log("participants => " + data);
    });

  this.showClient = true;

}

onRowEditCancel(client: any, index: number) {
  console.log("client"+client.quantity);
  console.log("index"+index);
  console.log(this.trade[index]);
  console.log(this.clonedClientTrades);
   this.trade[index].quantity = this.clonedClientTrades.quantity;
  //this.trade[index] = this.clonedClientTrades[client.tradeID];
 // delete this.clonedClientTrades[client.tradeID];
}
onRowDelete(client: any, index: number)
{
  alert("Are you sure?");
  console.log("transaction id"+this.trade[index].transactionid);
  this.http.post("http://localhost:8083/Settlement_System/rest/hello/deleteTransaction",{
    "transactionId":this.trade[index].transactionid
  }).subscribe(data=>{
    if(data==1)
    {
      this.deletedrow =true;
      this.ngOnInit();
    }
    else{
      this.deletedrow=false;
    }

    console.log(data);
  })
  console.log(this.trade[index]);
  delete this.trade[index];
 // this.constructor();
}



}
