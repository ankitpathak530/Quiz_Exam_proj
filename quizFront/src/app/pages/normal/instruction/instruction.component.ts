import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-instruction',
  templateUrl: './instruction.component.html',
  styleUrls: ['./instruction.component.css']
})
export class InstructionComponent implements OnInit {

  qId: any;
  quiz: any;

  constructor(private _router: ActivatedRoute,
    private _questionService: QuestionService,
    private _quizService: QuizService
  ) { }

  ngOnInit(): void {
    this.qId = this._router.snapshot.params.qid;
    console.info(this.qId);
    this._quizService.getQuiz(this.qId).subscribe(
      (data) => {

        this.quiz = data;
        this.quiz = this.quiz[0].quiz;

      },
      (error) => {
        console.log("error while laoding question");
      },
    );



  }

}
