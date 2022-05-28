package com.exam.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;


public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizzes();
	public Quiz getQuiz(long quizId);
	public boolean deleteQuiz(Long quizId);
	List<Quiz> getQuizzesOfCategory(Category category);
	List<Quiz> getQuizzesOfCategoryAndActive(Category category,Boolean isActive);
	
}
