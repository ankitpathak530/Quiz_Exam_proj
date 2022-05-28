import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {
  quizData: any;
  qId = 0;
  categories: any;

  constructor(private _route: ActivatedRoute,
    private _quizService: QuizService,
    private _categoryService: CategoryService,
    private _snack: MatSnackBar,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this.executeFirst();
  }



  executeFirst() {
    this.qId = this._route.snapshot.params.qid;

    this._quizService.getQuizById(this.qId).subscribe(
      (data) => {

        this.quizData = data;

        this._categoryService.categories().subscribe(
          (data) => {
            this.categories = data;
            console.log(this.quizData);
            console.log(this.categories);
          },
          (error) => {
            console.error("Category Loading failed");
          },
        );
      },
      (error) => {
        console.error("Quiz Loading failed.");
      },
    )
  }


  updateQuiz() {
    //Validate
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

    // sending quiz to server
    return this._quizService.updateQuiz(this.quizData).subscribe(
      (data) => {
        Swal.fire("success", "Quiz Update Successfully", "success").then((e: any) => {
          this._router.navigate(['/admin/view-quizzes'])
        });
      },
      (error) => {
        Swal.fire("error", "Quiz Update failed", "error");
      },
    )
  }


}
