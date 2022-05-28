package com.exam.controller;

import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

import lombok.AllArgsConstructor;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	private final CategoryService categoryService;

	
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		LOGGER.info("Category Controller addCategory called...");
		return ResponseEntity.ok(this.categoryService.addCategory(category));
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long cid) {
		LOGGER.info("Category Controller getCategory called...");
		Category category = this.categoryService.getCategory(cid);
		if (category == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(category);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllCategories() {
		LOGGER.info("Category Controller getAllCategory called...");
		Set<Category> categories = this.categoryService.getAllCategory();
		return ResponseEntity.ok(categories);
	}

	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		LOGGER.info("Category Controller updateCategory called...");
		return this.categoryService.updateCategory(category);
	}

	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		LOGGER.info("Category Controller deleteCategory called...");
		this.categoryService.deleteCategory(categoryId);
	}

}
