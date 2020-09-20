import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-obligation-report',
  templateUrl: './obligation-report.component.html',
  styleUrls: ['./obligation-report.component.css']

})
export class ObligationReportComponent implements OnInit {
  client=[
    { "name":"Citi", "funds":"300000"},
    { "name":"GS", "funds":"300000"},
    { "name":"Citi", "funds":"300000"},
    { "name":"JPMC", "funds":"300000"},
    { "name":"Citi", "funds":"300000"},

  ];
  
  nettedShares= [
    {"share":"Apple", "Qty":"100"},
    {"share":"Infosys", "Qty":"200"},
    {"share":"Microsoft", "Qty":"1500"},
    {"share":"Ibulls", "Qty":"5100"},
    {"share":"LT", "Qty":"105"},
    {"share":"Apple", "Qty":"100"},
    {"share":"Infosys", "Qty":"200"}
  ];
  cols=[
    {field:'share', header:"Stock Name"},
    {field:'Qty', header:"Quantity "},
   
  ];
  constructor() { }

  ngOnInit() {
  }

}
