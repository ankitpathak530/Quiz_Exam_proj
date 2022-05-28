import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{

  constructor(private snack: MatSnackBar,private login:LoginService,private router:Router) { }

  ngOnInit(): void {
  }

  public loginData={
    username: '',
    password: '',
  }


  onSubmit(){
    console.log("Logined");
    return 1;
  }

  formSubmit(){
    console.log("Form submited");
    if(this.loginData.username.trim() == '' || this.loginData.username.trim() == null ||
       this.loginData.password.trim() == '' || this.loginData.password.trim() == null)
     {
          this.snack.open('Username or password is empty',"",{
            duration:3000,
          });
           return;
     }
    else
    {
      //Request to server to generate token
      this.login.generateToken(this.loginData).subscribe(
        (data:any)=>{
              console.log("success.....");

              // save token
              this.login.setToken(data.token);

              this.login.getCurrentUser().subscribe((user:any)=>{
                this.login.setUser(user);

                if(this.login.getUserRole() == 'ADMIN')
                  { //redirect if user is admin admin_dashboard
                         this.login.loginStatusSubject.next(true);
                          this.router.navigate(['admin']);
                         
                  }
                  else if(this.login.getUserRole() == 'NORMAL')
                  { // redirect if User is normal normal_dahboard
                           this.login.loginStatusSubject.next(true);
                           this.router.navigate(['normal-user']);
                          
                  }
                  else{
                    this.login.logOut();
                  }
              });
        },
        (error:any)=>{
              Swal.fire("Something went wrong!", "Username or password is invalid !", "error");
        }
      );
    }



  }








}