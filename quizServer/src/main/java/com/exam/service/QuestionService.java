package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.helper.Result;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;



public interface QuestionService {

	 public Question addQuestion(Question question);
	 public Question updateQuestion(Question question);
	 public Set<Question> getAllQuestions();
	 public Question getQuestion(Long questionId);
     public void deleteQuestion(Long questionId);
	
	 public Result verifyAnswerSheet(Long quizId, List<Question> questions);
	 

	public Set<Question> getQuestionOfQuiz(long quizId);
	 
}
