package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.controller.AuthenticateController;
import com.exam.controller.HomeController;
import com.exam.helper.Result;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.repo.QuizRepository;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private final QuestionRepository questionRepo;
    private final QuizService quizService;
	private final AuthenticateController authenticateController;
	
	@Override
	public Question addQuestion(Question question) {
		LOGGER.info("Question Service addQuestion Called...");
		return this.questionRepo.save(question);
	}

	
	
	
	@Override
	public Question updateQuestion(Question question) {
		LOGGER.info("Question Service updateQuestion Called...");
		return this.questionRepo.save(question);
	}
	
	
	

	@Override
	public Set<Question> getAllQuestions() {
		LOGGER.info("Question Service getAllQuestions Called...");
		return new LinkedHashSet<>(this.questionRepo.findAll());
	}
	
	
	

	@Override
	public Question getQuestion(Long questionId) {
		LOGGER.info("Question Service getQuestion Called...");
		return this.questionRepo.findById(questionId).get();
	}

	

	
	


	
	
	@Override
	public Set<Question> getQuestionOfQuiz(long quizId) {
		Quiz quiz = new Quiz();
		quiz.setQId(quizId);
		 Set<Question> questions = this.questionRepo.findByQuiz(quiz).stream().collect(Collectors.toSet());
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String authority = authentication.getAuthorities().stream().map(e->e.getAuthority().toString()).collect(Collectors.toList()).get(0);
		 
		 if(authority.equals("NORMAL")) {
			 questions.forEach(e->e.setAnswer(""));
		 }
		 return questions;
	}


	//with answers
	private Set<Question> getQuestionOfQuizWithAns(long quizId) {
		Quiz quiz = new Quiz();
		quiz.setQId(quizId);
		 Set<Question> questions = this.questionRepo.findByQuiz(quiz).stream().collect(Collectors.toSet());
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String authority = authentication.getAuthorities().stream().map(e->e.getAuthority().toString()).collect(Collectors.toList()).get(0);
		 
		 return questions;
	}


	
	
	 
	
	
	@Override
	public Result verifyAnswerSheet(Long quizId, List<Question> questions) {

		LOGGER.info("Service VerifyAnsserSheet Called...");
		List<Question> questionOfQuiz = getQuestionOfQuizWithAns(quizId).stream().collect(Collectors.toList());
		
		questions.sort((Question q1, Question q2) -> q1.getQuesId().intValue() - q2.getQuesId().intValue());
		
		questionOfQuiz.sort((Question q1, Question q2) -> q1.getQuesId().intValue() - q2.getQuesId().intValue());
		
        questions.forEach(e->e.setQuiz(null));
		questionOfQuiz.forEach(e->e.setQuiz(null));
        
        
		LOGGER.error(questions.toString());
		LOGGER.error(questionOfQuiz.toString());
		
		
		int i = 0;
		Long ca = 0l, na = 0l;
		Long total = Long.parseLong(Integer.toString(questionOfQuiz.size()));


		for (Question q : questionOfQuiz) {
			if (questions.get(i).getAnswer() == null || questions.get(i).getAnswer().equals("")) {
				na++;
				i++;
			} else if (q.getAnswer().equals(questions.get(i++).getAnswer())) {
				ca++;
			}
		}
		
		Result result = new Result();
		result.setTotalQuestion(total);
		result.setNonAttemped(na);
		result.setAttemped(total - na);
		result.setCorrectAttemped(ca);

		return result;
	}
	
	
	


	 

	
	@Override
	public void deleteQuestion(Long questionId) {
		LOGGER.info("Question Service deleteQuestion Called...");
		Question question = new Question();
		question.setQuesId(questionId);
		this.questionRepo.delete(question);
	}

}
