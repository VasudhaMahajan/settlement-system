import { Component, OnInit } from '@angular/core';
import {Trade} from '../trade';
import {DynamicDialogRef} from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import {TradeService} from '../../service/tradeService';

@Component({
  selector: 'app-add-transaction-form',
  templateUrl: './add-transaction-form.component.html',
  styleUrls: ['./add-transaction-form.component.scss']
})
export class AddTransactionFormComponent implements OnInit {

  trade = {
    TransactionId :0,
    securityQuantity:0 ,
    SecurityRate :0,
    SSIN_Id:999,
  }
  TransactionId;
  securityQuantity;
  SecurityRate;
  SSIN_Id:999;

  transaction;
  text:any;
  securities;
  participants;
  participants1;
  dataToSend;
  selectedSecurity;
  selectedBuyer;
  selectedSeller;
  constructor(public ref: DynamicDialogRef,private http:HttpClient,private tradeService:TradeService) { 
    //this.tradeService.getTransactionData().then(trade => this.securities = trade);
    this.http.get("http://localhost:8083/Settlement_System/rest/hello/getAllSecurities").subscribe(
      data=> {
        this.securities = data;
        console.log(data);
      });
      this.http.get("http://localhost:8083Settlement_System/rest/ParticipantFirst/getAllParticipants").subscribe(
      data=> {
        this.participants = data;
        this.participants1 = data;
        console.log(data);
      });
  }
  sendData()
  {
  
    this.transaction =  {
        transactionid : this.trade.TransactionId,
        SSIN_Id : 15,
        sellerId : this.selectedSeller.participantId,
        sellerName : this.selectedSeller.participantName ,
        buyerId: this.selectedBuyer.participantId,
        buyerName :this.selectedBuyer.participantName,
        securityId :this.selectedSecurity.securityId,
        securityName :this.selectedSecurity.securityName,
        securityQuantity:this.trade.securityQuantity, 
        SecurityRate:this.trade.SecurityRate,
        transactionAmount : (this.trade.securityQuantity * this.trade.SecurityRate),
      }
      console.log(this.transaction);
    this.http.post("http://localhost:8083/Settlement_System/rest/hello/addTransaction",this.transaction
    
    ).subscribe(data=>{
      console.log(this.transaction);
      console.log(data);
    })
    this.ref.close(this.transaction);

  }
  ngOnInit() {
    
  }
  

}
