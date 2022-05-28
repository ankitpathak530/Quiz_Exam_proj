import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'normal-app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  categories:any;

  constructor(private _category:CategoryService,
              private _snak:MatSnackBar ) { }

  ngOnInit(): void {

      this._category.categories().subscribe(
        (data)=>{
           this.categories = data;
        },
        (error)=>{
           this._snak.open("Error while loading category from server.","",{
               duration:3000,
           });
        },
      )
  }

}
