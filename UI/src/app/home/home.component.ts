import { Component, OnInit } from '@angular/core';
import { SelectItem } from '../SelectItem';
import {Car} from '../car';
import {CarService} from '../../service/carservice';
import{Trade} from '../trade';
import {TradeService} from '../../service/tradeService';
import {DialogService} from 'primeng/api';
import {AddTransactionFormComponent} from '../add-transaction-form/add-transaction-form.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [DialogService]
})
export class HomeComponent implements OnInit {

  cars: Car[];
  trade;
  cols: any[];

  
constructor(private tradeService:TradeService,public dialogService: DialogService) { }

ngOnInit() {
  this.tradeService.getTransactionJSON().subscribe(trade =>{
    this.trade = trade;
    console.log(this.trade);
  })
  //this.tradeService.getTransactionData().then(trade => this.trade = trade);

  this.cols = [
      { field: 'tradeID', header: 'Trade ID' },
      {field: 'security', header: 'Security' },
      { field: 'quantity', header: 'Quantity' },
      { field: 'price', header: 'Price' },
      { field: 'buyer', header: 'Buyer Clearing Member' },
      { field: 'seller', header: 'Seller Clearing Member' }

  ];

  }
  viewShortages()
  {
    this.tradeService.getJsonData().subscribe(data=> console.log(data));
  }
  AddTransaction()
  {
    const ref = this.dialogService.open(AddTransactionFormComponent, {
      header: 'Fill the transaction form',
      width: '50%',
      // contentStyle: {"max-height": "350px", "overflow": "auto"}
  });

  ref.onClose.subscribe((data) =>{

    console.log(data.tradeID);
   
    data.tradeID = this.trade[this.trade.length-1].tradeID + 1;

    this.trade.push(data);
    //console.log(this.trade);
      // if (car) {
      //     this.messageService.add({severity:'info', summary: 'Car Selected', detail:'Vin:' + car.vin});
      // }
  });
}
  
}