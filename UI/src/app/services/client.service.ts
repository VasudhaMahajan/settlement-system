import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private url_trans = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getAllTransactionsByParticipantId";
  private url_clientInfo = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getParticipantById";
  private url_netFunds = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getNetFunds";
  private url_netSec = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getNetSecurities";
  private url_caction="http://localhost:8083/Settlement_System/rest/ParticipantFirst/getCorporateActionsByParticipantId"
  private url_sec = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getParticipantSecuritiesById";
  private url_rights = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/excuteRights"; 
  private url_addfun = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/updateFunds";
  private url_fund_obli = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/viewFundObligationReport";
  private url_share_obli = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/viewSecuritiesObligationReport";
  private url_cos = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/viewCostOfsettlementReport";
  private url_cossec = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/viewCostOfsettlementReportSecurities";
  constructor(private http : HttpClient) { }

  getClientInformation(id)
  {
    return this.http.post(this.url_clientInfo,id);
  }
  getAllTrades(id)
  {
    console.log(id);
    return this.http.post(this.url_trans , id);
  }
  getNettingDetailsFunds(id)
  {
    return this.http.post(this.url_netFunds,id);
  }
  getNettingDetailsSecurity(id)
  {
    return this.http.post(this.url_netSec,id);

  }
  getCorporateAction(id)
  {
    return this.http.post(this.url_caction,id);
  }

  getSecurities(id)
  {
    return this.http.post(this.url_sec,id);
  }

  executeRights(info)
  {
    return this.http.post(this.url_rights,info);
  }
  addMoreFunds(funds)
  {
    return this.http.post(this.url_addfun,funds);
  }

  viewFundObligationReport(id)
  {
    return this.http.post(this.url_fund_obli , id);
  }
  viewShareObligation(id)
  {
    return this.http.post(this.url_share_obli,id);
  }
  costOfSettlement(id)
  {
    return this.http.post(this.url_cos,id); 
  }
  costOfSettlementSec(id)
  {
    return this.http.post(this.url_cossec,id);
  }

}
