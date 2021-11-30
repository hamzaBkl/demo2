package com.example.demo2.service;

import java.util.List;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.model.Category;



public class Test{

	public static void main(String[] args) {
		CategoryDAO catDAO = new CategoryDAOImpl();
		List<Category> categories;
		Category  uneCategory;
		categories= catDAO.listCategories();
		System.out.println("List of all Categorys (_"+categories.size()+"_)");
		categories.forEach(System.out::println);
		
		uneCategory = new Category();
		uneCategory.setNameCat("Nouvelle Category");
		uneCategory.setDescription("Nouvelle Category");
		catDAO.addCategory(uneCategory);

		categories= catDAO.listCategories();
		System.out.println("List of all Categorys (_"+categories.size()+"_)");
		categories.forEach(System.out::println);
		
		uneCategory = catDAO.getCategoryById(2);
		uneCategory.setDescription("Boitier Tower Big or Slim");
		catDAO.updateCategory(uneCategory);

		categories= catDAO.listCategories();
		System.out.println("List of all Categories (_"+categories.size()+"_)");
		categories.forEach(System.out::println);
	}

}
