import { HttpClient } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }

    //load all category
    public categories()
    {
        return this.http.get(`${baseUrl}/category/`)
    }

    //Add new Category
    public addCategory(Category:any)
    {
        return this.http.post(`${baseUrl}/category/`,Category);
    }

    public deleteCategory(cid:any)
    {
      return this.http.delete(`${baseUrl}/category/${cid}`);
    }

    
}
