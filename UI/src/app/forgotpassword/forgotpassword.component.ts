import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {
message;
flag=false;
  constructor() { }
  handleClick(){
    this.flag=true;
  }
  ngOnInit() {
  }

}
// export class CaptchaDemo {
//   MessageService;
//   constructor(private messageService: MessageService) {}

//   showResponse(event) {
//       this.messageService.add({severity:'info', summary:'Succees', detail: 'User Responded', sticky: true});
//   }
// }
