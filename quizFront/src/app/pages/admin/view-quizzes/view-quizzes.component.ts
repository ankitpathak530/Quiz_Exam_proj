import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes: any;

  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    this.executeFirst();
  }

  executeFirst() {
    this.quizService.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data;
      },
      (error) => {
        Swal.fire("Error!", "Server Error", "error");
      },
    )
  }


  deleteQuiz(qId: any) {

    Swal.fire({
      title: 'Do you want to Delete the Quiz?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'No',
      denyButtonText: `Yes`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isDenied) {

        this.quizService.deleteQuiz(qId).subscribe(
          (data) => {
            this.quizzes = this.quizzes.filter((quiz: any) => quiz.qid != qId);
            Swal.fire("success", "Quiz Deleted", "success");
          },
          (error) => {
            Swal.fire("error", "Quiz not deleted", "error");
          },
        )

      } else if (result.isConfirmed) {

        Swal.fire('Relax ! Your quiz is safe', '', 'info')
      }
    })






  }




}
