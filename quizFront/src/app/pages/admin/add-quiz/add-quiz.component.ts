import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {


  categories: any;

  quizData = {
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active: true,
    category: {
      cid: '',
    },
  };



  constructor(private categoryService: CategoryService, private _snack: MatSnackBar, private _quizService: QuizService) { }

  ngOnInit(): void {
    this.categoryService.categories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error) => {
        Swal.fire("Error!", "No category to add quiz", "error");
      }

    );
  }

  addQuiz() {
    if (this.quizData.title.trim() == '' || this.quizData.title == null ||
      this.quizData.maxMarks <= '0' || this.quizData.maxMarks == null ||
      this.quizData.numberOfQuestions <= '0' || this.quizData.numberOfQuestions == null ||
      this.quizData.category.cid == null || this.quizData.category.cid == ''
    ) {
      this._snack.open("Please recheck mandatory fields", '0k', {
        duration: 3000,
      })
      return;
    }

    // adding quiz to database server
    this._quizService.addQuiz(this.quizData).subscribe(
      (data) => {
        Swal.fire("success", "Quiz Added Successfully", "success");
      },
      (error) => {
        Swal.fire("Error", "Something went wrong", "error");
      }
    )

  }




}
