package com.exam.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.exam.controller.AuthenticateController;
import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class); 
	private final CategoryRepository categoryRepository;

	
	
	@Override
	public Category addCategory(Category category) {
		LOGGER.info("Category Service addCategory called..");
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		LOGGER.info("Category Service updateCategory called..");
		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getAllCategory() {
		LOGGER.info("Category Service getAllCategory called..");
		return new LinkedHashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategory(long cid) {
		LOGGER.info("Category Service getCategory called..");
		return this.categoryRepository.findById(cid).orElse(null);
	}

	@Override
	public boolean deleteCategory(long cid) {
		LOGGER.info("Category Service deleteCategory called..");
		try {
			this.categoryRepository.deleteById(cid);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
