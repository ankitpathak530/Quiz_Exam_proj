import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  isSubmit = false;
  qId: any;
  questions: any;

  co: any;
  result: any;
  maxMarks = 0;
  noOfQuestions = 0;
  attemped = 0;
  nonAttemped = 0;
  correctAttemped = 0;

  timer: any;
  questionLength: any;

  constructor(private _router: ActivatedRoute,
    private _questions: QuestionService,
    private _locationStrategy: LocationStrategy,
    private _loginService: LoginService) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.qId = this._router.snapshot.params.qId;
    this.loadQuestions();
  }


  // Define a function to handle back button and use anywhere
  preventBackButton() {
    history.pushState(null, "", location.href);
    this._locationStrategy.onPopState(() => {
      history.pushState(null, "", location.href);
    })
  }

  //load questions of quiz
  loadQuestions() {
    this._questions.getQuestionOfQuiz(this.qId).subscribe(
      (data) => {
        this.questions = data;
        this.questionLength = Object.keys(this.questions).length
        this.timer = this.questionLength * 60;

        if (this.isSubmit == false) {
          this.startTimer();
        }


        this.questions.forEach((e: any) => {
          e['givenAnswer'] = '';
        });
        console.log(this.questions);
      },
      (error) => {
        console.log("error while loading ");
      },
    )
  }


  onSubmitResult() {
    Swal.fire({
      title: 'Do you want to Submit the Quiz?',
      showCancelButton: true,
      confirmButtonText: 'Yes',
    }).then((result) => {
      if (result.isConfirmed) {

        this.submitQuiz();
      }
    });
  }



  submitQuiz() {
    this._questions.verifyAnswerOfQuiz(this.qId, this.questions).subscribe(
      (data) => {
        this.result = data;

        this.maxMarks = this.questions[0].quiz.maxMarks;
        this.noOfQuestions = this.result.totalQuestion;
        this.attemped = this.result.attemped;
        this.nonAttemped = this.result.nonAttemped;
        this.correctAttemped = this.result.correctAttemped;
        //    console.log("Final this is your result----->", data);
        //    console.log("Total Questions:-", this.noOfQuestions, "\nMax marks:-", this.maxMarks, "\nAttemped:->", this.attemped, "\nNon Attemped:->", this.nonAttemped, "\nCorrect Answer:-", this.correctAttemped);
        this.isSubmit = true;

      },
      (error) => {
        Swal.fire("error", "Error while submiting answersheet");
        this.logout();
      },
    )

  }



  logout() {
    this._loginService.logOut();
    window.location.reload();
  }



  startTimer() {

    let t = window.setInterval(() => {
      if (this.timer <= 0) {
        this.submitQuiz();
        clearInterval(t);
      }
      else {
        this.timer--;
      }

    }, 1000);
  }


  formateTime() {
    let mm = Math.floor(this.timer / 60);
    let ss = this.timer - mm * 60;
    return `${mm} Min : ${ss} Sec`;
  }


}
