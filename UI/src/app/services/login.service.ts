import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = "http://localhost:8083/Settlement_System/rest/ParticipantFirst/getLoginInfo";
  constructor(private http : HttpClient) { }

  getLogin(user)
  {
    return this.http.post(this.url,user);
  }
}
