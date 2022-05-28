import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
   providedIn: 'root'
})
export class QuizService {

   constructor(private http: HttpClient) { }

   public quizzes() {
      return this.http.get(`${baseUrl}/quiz/`,);
   }

   public addQuiz(quiz: any) {
      return this.http.post(`${baseUrl}/quiz/`, quiz);
   }

   public deleteQuiz(qId: any) {
      return this.http.delete(`${baseUrl}/quiz/${qId}`);
   }

   public getQuiz(qId: any) {
      return this.http.get(`${baseUrl}/question/quiz/${qId}`);
   }

   public getQuizById(qId: any) {
      return this.http.get(`${baseUrl}/quiz/${qId}`);
   }

   public updateQuiz(quiz: any) {
      return this.http.put(`${baseUrl}/quiz/`, quiz);
   }

   public getQuizzesOfCategory(cid: any) {
      return this.http.get(`${baseUrl}/quiz/category/${cid}`);
   }

   public getQuizzesOfCategoryAndActive(cid: any, isActive: any) {
      return this.http.get(`${baseUrl}/quiz/category/${cid}/${isActive}`);
   }

}
