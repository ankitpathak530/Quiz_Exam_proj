package com.exam.controller;


import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.helper.ExamException;
import com.exam.helper.Result;
import com.exam.model.User;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.MailService;
import com.exam.service.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@AllArgsConstructor
public class QuestionController {

	
	 private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	 private final QuestionService questionService;
	 private final MailService mailService;

	 
	 private final AuthenticateController authenticateController;
	   
	 

	 
	 //Add Question
	 @PostMapping("/")
	 public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	 {
		   LOGGER.info("Question Controller addQuestion called..."+question.toString());
		   return ResponseEntity.ok(this.questionService.addQuestion(question));
	 }
	 
	 @PutMapping("/")
	 public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
	 {
		   LOGGER.info("Question Controller updateQuestion called...");
		   return ResponseEntity.ok(this.questionService.addQuestion(question));
	 }

	 
	 
	 @PostMapping("/{quizId}")
	 public ResponseEntity<Question> addQuestionInQuiz(@PathVariable("quizId") Long quizId, @RequestBody Question question)
	 {
		   Quiz quiz = new Quiz();
		   quiz.setQId(quizId);
		   question.setQuiz(quiz);
		   LOGGER.info("Question Controller addQuestion called..."+question.toString());
		   return ResponseEntity.ok(this.questionService.addQuestion(question));
	 }


	 
	
	 
	 
 

	 
	 //Get all  Questions of any quiz with no answers
	 @GetMapping("/quiz/{quizId}")
	 public ResponseEntity<?> getAllQuestionsOfAnyQuizSuffle(@PathVariable Long quizId)
	 {
		   LOGGER.info("Question Controller getAllQuestionsOfAnyQuizSuffle called start");
		   return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(quizId));
	 }

	 
	 
	 
	 
	  //Get all  Questions of with answers
		 @GetMapping("/quiz/all/{quizId}")
		 public ResponseEntity<Set<Question>> getQuestionOfQuizAdmin(@PathVariable Long quizId)
		 {
			   return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(quizId));
		 }
		 
		 
		 
	 
	 @PostMapping("/quiz/verifyAnswerSheet/{quizId}")
	 public Result verifyAnswerSheet(@PathVariable Long quizId,@RequestBody List<Question> questions) throws ExamException
	 {
		 LOGGER.info("Question Controller verifyAnswerSheet called...");
		 Result result =  this.questionService.verifyAnswerSheet(quizId, questions);
		 
		 //fetching currently active user email id to send result report on mail.
		 Principal principal = SecurityContextHolder.getContext().getAuthentication();
		 User currentUser = this.authenticateController.getCurrentUser(principal);
		 this.mailService.sendMail(currentUser.getEmail(), result.toString() , "Final Result of Quiz");
		 return result;
	 }
	 
	 
	 
	 
	 //Get Single Question
	 @GetMapping("/{questionId}")
	 public Question get(@PathVariable("QuestionId") Long quesId)
	 {
		 LOGGER.info("Question Controller get called...");
		 return this.questionService.getQuestion(quesId);
	 }
	 
	 
	 
	 
	 //Delete Question
	 @DeleteMapping("/{questionId}")
	 public void getQuestionsOfQuiz(@PathVariable("questionId") Long questionId)
	 {
		 LOGGER.info("Question Controller getQuestionsOfQuiz called...");
		 this.questionService.deleteQuestion(questionId);
	 }

	 
} 