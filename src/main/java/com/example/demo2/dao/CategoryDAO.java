package com.example.demo2.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.demo2.model.Category;

public interface CategoryDAO {
	void addCategory(Category category);
	void updateCategory(Category category);
	List<Category> listCategories();
	List<Category> selectCatByKeyword(String keyWord);
	Category getCategoryById(long id);
	void removeCategory(long id);
}
