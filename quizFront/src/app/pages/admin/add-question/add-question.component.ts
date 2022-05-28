import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {


  quizId: any;
  title: any;

  question =
    {
      content: '',
      option1: '',
      option2: '',
      option3: '',
      option4: '',
      answer: '',
    };

  constructor(private _router: ActivatedRoute,
    private _questionService: QuestionService,
    private _matSnake: MatSnackBar) { }

  ngOnInit(): void {
    this.quizId = this._router.snapshot.params.qid;
    this.title = this._router.snapshot.params.title;


  }

  saveQuestionOfQuiz() {

    if (this.question.content == '' || this.question.content == null ||
      this.question.option1 == '' || this.question.option1 == null ||
      this.question.option2 == '' || this.question.option2 == null ||
      this.question.option3 == '' || this.question.option3 == null ||
      this.question.option4 == '' || this.question.option4 == null ||
      this.question.answer == '' || this.question.answer == null
    ) {
      this._matSnake.open("Check mandetory field!", "Ok", {
        duration: 3000,
      });
      return;
    }
    console.log("inside add ques   " + this.question + "  " + this.quizId);
    this._questionService.addQuestionOfQuiz(this.question, this.quizId).subscribe(
      (data) => {
        Swal.fire("Success", "Question Added To Quiz", "success");
      },
      (error) => {
        Swal.fire("error", "Something went wrong!", "error");
      }
    )
  }


}
