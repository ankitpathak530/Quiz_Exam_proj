package com.exam.service;

import java.util.Set;
import com.exam.model.exam.Category;


public interface CategoryService {

	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Category getCategory(long cid);
	public boolean deleteCategory(long cid);
	public Set<Category> getAllCategory();
	
}
