import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username;
  private password;

  
  constructor(private http:HttpClient,private router : Router) { }

  ngOnInit() {
  }
  onLogin()
  {
    
  

    let user = {
      "username":this.username , "password":this.password
    }
    console.log(user);
    this.http.post("http://localhost:8083/Settlement_System/rest/ParticipantFirst/getLoginInfo",user).subscribe(data=>
      {
        if(data > 0){
          console.log("valid");
          this.router.navigateByUrl('/client');
          
        }
        else if(data == -1)
        {
          this.router.navigateByUrl('/home');
        }
        else{
          this.router.navigateByUrl('/');
        }
      })
  }
}
