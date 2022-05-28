import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }



  //Generate Token
  public generateToken(loginData: any) {

    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }



  // Login user: set token in local storage
  public setToken(token: any) {
    localStorage.setItem("token", token);
  }
  public getToken() {
    return localStorage.getItem("token");
  }


  // IsLogin: user is login or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem("token");
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    }
    else {
      return true;
    }
  }

  public logOut() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }



  public setUser(user: any) {
    localStorage.setItem("user", JSON.stringify(user));
  }

  public getUser() {
    let userStr = localStorage.getItem("user");
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logOut();
      return null;
    }
  }
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }






}
