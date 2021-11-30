package com.example.demo2.service;

import java.util.List;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.model.Category;



public class Test{

	public static void main(String[] args) {
		CategoryDAO catDAO = new CategoryDAOImpl();
		List<com.example.demo2.model.Category> lesCateg;
		Category  uneCategory;
		lesCateg= catDAO.listCategories();
		System.out.println("List of all Categorys (_"+lesCateg.size()+"_)");
		lesCateg.stream().forEach(e -> System.out.println(e));
		
		uneCategory = new Category();
		uneCategory.setTitle("Nouvelle Category");
		uneCategory.setDescription("Nouvelle Category");
		catDAO.addCategory(uneCategory);
		
		lesCateg= catDAO.listCategories();
		System.out.println("List of all Categorys (_"+lesCateg.size()+"_)");
		lesCateg.stream().forEach(e -> System.out.println(e));
		
		uneCategory = catDAO.getCategoryById(2);
		uneCategory.setDescription("Boitier Tower Big or Slim");
		catDAO.updateCategory(uneCategory);
		
		lesCateg= catDAO.listCategories();
		System.out.println("List of all Categorys (_"+lesCateg.size()+"_)");
		lesCateg.stream().forEach(e -> System.out.println(e));
	}

}
