import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService, private snack: MatSnackBar) { }

  public user = {
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    username: '',
    password: '',
    about: '',
  }

  ngOnInit(): void { }

  formSubmit() {
    if (this.user.firstName == '' || this.user.lastName == '' ||
      this.user.email == '' || this.user.phone == '' ||
      this.user.username == '' || this.user.password == '') {
      this.snack.open("Important input field can't be empty", "", {
        duration: 2000,
      });
      return;
    }

    this.userService.addUser(this.user).subscribe(
      (data) => {
        console.log(data);
        Swal.fire("Good job!", "User is registered!", "success");
      },
      (error) => {
        console.log(error);
        this.snack.open("This user is already exist! Try another", "Ok",)
      }
    );
  }




}
