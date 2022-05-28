package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.exam.controller.HomeController;
import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

	private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private final QuizRepository quizRepository;

	
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		LOGGER.info("Quiz Service addQuiz Called...");
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		LOGGER.info("Quiz Service updateQuiz Called...");
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		LOGGER.info("Quiz Service getQuizzes Called...");
		return new LinkedHashSet<>(this.quizRepository.findAll());
	}

	@Override
	public  Quiz getQuiz(long quizId) {
		LOGGER.info("Quiz Service getQuiz Called...");
		Quiz quiz = this.quizRepository.findById(quizId).orElse(null);
		
		
		//Collections.shuffle(list); // Like LUDO
		LOGGER.info("Quiz Service gatQuiz completed..."+quiz);
		return  quiz;
	}

	@Override
	public boolean deleteQuiz(Long quizId) {
		LOGGER.info("Quiz Service deleteQuiz Called...");
		try {
			this.quizRepository.deleteById(quizId);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		LOGGER.info("Quiz Service getQuizzesOfCategory Called...");
		return this.quizRepository.findByCategory(category);
	}

	@Override
	public List<Quiz> getQuizzesOfCategoryAndActive(Category category, Boolean isActive) {
		LOGGER.info("Quiz Service getQuizzesOfCategoryAndActive Called...");
		return this.quizRepository.findByCategoryAndActive(category, isActive);
	}

}
