import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {
  catId: any;
  quizzes: any;
  quizzesLength = 0;

  constructor(private _router: ActivatedRoute,
    private _QuizService: QuizService) { }

  ngOnInit(): void {


    this._router.params.subscribe((params) => {
      this.catId = params.catId;

      this._QuizService.getQuizzesOfCategoryAndActive(this.catId, true).subscribe(
        (data) => {
          this.quizzes = data;
          this.quizzesLength = Object.keys(this.quizzes).length;
        },
        (error) => {
          alert("error while loading quiz");
        },
      );



    });




  }//end of ngOnInit() method

}
