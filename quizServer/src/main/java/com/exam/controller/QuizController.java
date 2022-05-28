package com.exam.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/quiz")
@CrossOrigin("*")
@AllArgsConstructor
public class QuizController {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	private final QuizService quizService;

	
	
	// Add Quiz
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		LOGGER.info("Quiz Controller addQuiz called...");
		try {
			return ResponseEntity.ok(this.quizService.addQuiz(quiz));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Update Quiz
	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {
		LOGGER.info("Quiz Controller updateQuiz called...");
		try {
			return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Get All Quizzes
	@GetMapping("/")
	public ResponseEntity<?> getAllQuiz() {
		LOGGER.info("Quiz Controller getAllQuiz called...");
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}

	// Get Single Quiz
	@GetMapping("/{quizId}")
	public ResponseEntity<?> getQuiz(@PathVariable("quizId") Long quizId) {
		LOGGER.info("Quiz Controller getQuiz called...");
		try {
			return ResponseEntity.ok(this.quizService.getQuiz(quizId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Delete Quiz
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId) {
		LOGGER.info("Quiz Controller deleteQuiz called...");
		this.quizService.deleteQuiz(quizId);
	}

	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
		LOGGER.info("Quiz Controller getQuizzesOfCategory called...");
		Category cat1 = new Category();
		cat1.setCid(cid);
		return this.quizService.getQuizzesOfCategory(cat1);
	}

	@GetMapping("/category/{cid}/{isActive}")
	public List<Quiz> getQuizzesOfCategoryAndActive(@PathVariable("cid") Long cid,
			@PathVariable("isActive") Boolean isActive) {
		LOGGER.info("Quiz Controller getQuizzesOfCategoryAndActive called...");
		Category cat1 = new Category();
		cat1.setCid(cid);
		return this.quizService.getQuizzesOfCategoryAndActive(cat1, isActive);
	}

}
