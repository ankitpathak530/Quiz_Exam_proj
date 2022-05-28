import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
     providedIn: 'root'
})
export class QuestionService {

     constructor(private _http: HttpClient) { }

     public getQuestionOfQuiz(quizId: any) {
          return this._http.get(`${baseUrl}/question/quiz/all/${quizId}`);
     }

     public addQuestionOfQuiz(question: any, quizId: any) {
          console.log("question-->" + question);
          return this._http.post(`${baseUrl}/question/${quizId}`, question);
     }

     public deleteQuestionOfQuiz(qId: any) {
          return this._http.delete(`${baseUrl}/question/${qId}`);
     }

     public verifyAnswerOfQuiz(quizId: any, answerSheet: any) {

          return this._http.post(`${baseUrl}/question/quiz/verifyAnswerSheet/${quizId}`, answerSheet)
     }


}
