package com.example.demo2.dao;

import java.util.List;

import com.example.demo2.model.Category;

public interface CategoryDAO {
	public void addCategory(Category category);
	public void updateCategory(Category category);
	public List<Category> listCategories();
	public List<Category> selectCatByKeyword(String keyWord);
	public Category getCategoryById(int id);
	public void removeCategory(int id);
}
