import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

  categories:any;


  constructor(private category:CategoryService) { }

  ngOnInit(): void {
      this.category.categories().subscribe((data:any)=>{
            this.categories = data;
            console.log(data);
         },
         (error)=>{
             Swal.fire("Error!","Error while loading data","error");
         }
      );
  }



  deleteCategory(cid:any)
  {
    Swal.fire({
      title: 'Warning!...  Be careful..... All Quizzes and Questions releted to this Category will be deleted Permanentely.',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'No',
      denyButtonText: `Yes`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isDenied) {
       
        this.category.deleteCategory(cid).subscribe(
          (data)=>{
              this.categories = this.categories.filter((categories:any)=>categories.cid != cid);
              Swal.fire("Success","Category Deleted","success");
              console.log(data);
          },
          (error)=>{
              Swal.fire("error","Category not deleted","error");
          }

       )
      } 
      else if (result.isConfirmed) 
      {

           Swal.fire('Relax ! Your Category is safe', '', 'info')
      }
    })

  }

}
