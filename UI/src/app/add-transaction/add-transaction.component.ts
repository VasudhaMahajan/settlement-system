import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.scss']
})
export class AddTransactionComponent implements OnInit {

  trade = {
    TransactionId :11,
    securityQuantity:0 ,
    SecurityRate :0,
    SSIN_Id:999,
  }
  
  constructor() { }

  ngOnInit() {
  }

}
