import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Trade } from '../app/trade';
@Injectable()
export class TradeService {

    url:string;
    constructor(private http: HttpClient) { }

    
    getTransactionData(){
        return this.http.get<any>('assets/transaction-Table.json')
      .toPromise()
      .then(res => <Trade[]>res.data)
      .then(data => { return data; });
    
    }
    getTransactionJSON()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getAllTransactions";;
        return this.http.get(this.url);
    }
    getJsonData()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getStudent";
        return this.http.get(this.url);
    }
    addNewTransaction()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getStudent";
    }
    callNettingResult()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getNet";
        return this.http.get(this.url);
    }
    performSettelement()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/performSettlement";
        return this.http.get(this.url);
    }
    viewFundShortages()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getFundShortageResult";
        return this.http.get(this.url);
    }
    viewShareShortages()
    {
        this.url = "http://localhost:8083/Settlement_System/rest/hello/getSecuritiesShortageResult";
        return this.http.get(this.url);
    }
}