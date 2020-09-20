import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientSessionService {

  private clientId = 0;
  constructor() { }

  setClientId(id)
  {
    this.clientId = id;
  }
  getClientId()
  {
    return this.clientId;
  }
}
