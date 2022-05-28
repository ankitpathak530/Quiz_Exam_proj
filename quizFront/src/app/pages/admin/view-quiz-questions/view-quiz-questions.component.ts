import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {

  qId: any;
  qTitle: any;
  questions: any;



  constructor(private _route: ActivatedRoute,
    private _questionService: QuestionService,
  ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qid;
    this.qTitle = this._route.snapshot.params.title;

    this._questionService.getQuestionOfQuiz(this.qId).subscribe(
      (data) => {
        this.questions = data;
      },
      (error) => {
        Swal.fire("error", "Question loading failed", "error");
      },
    );
  }

  deleteQuestionOfQuiz(quesId: any) {

    Swal.fire({
      title: 'Do you want to Delete this Question?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'No',
      denyButtonText: `Yes`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isDenied) {

        this._questionService.deleteQuestionOfQuiz(quesId).subscribe(
          (data) => {
            this.questions = this.questions.filter((question: any) => question.quesId != quesId);
            Swal.fire("Success", "Question Deleted", "success");
          },
          (error) => {
            Swal.fire("error", "Question not deleted", "error");
          }

        )
      }
      else if (result.isConfirmed) {

        Swal.fire('Relax ! Your Question is safe', '', 'info')
      }
    })


  }




  updateQuestionOfQuiz(quesId: any) {

  }



}
