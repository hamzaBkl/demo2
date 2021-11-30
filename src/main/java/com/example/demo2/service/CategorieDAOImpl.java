package com.example.demo2.service;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.model.Category;



public class CategoryDAOImpl implements CategoryDAO {
	private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not create SessionFactory", e);
			throw new IllegalStateException("Could not create SessionFactory");
		}
	} 
	
	@Override
	public void addCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(category);
		session.getTransaction().commit();
		logger.info("Category saved successfully, Category Details="+category);
	}

	@Override
	public void updateCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(category);
		session.getTransaction().commit();
		logger.info("Category updated successfully, Category Details="+category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listCategorys() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Category> CategorysList = session.createQuery("from Category").list();
		session.getTransaction().commit();
		
		return CategorysList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> selectCatByKeyword(String keyWord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Category> CategorysList = session.createQuery("from Category c WHERE c.nameCat LIKE '%"+keyWord+"%'").list();
		session.getTransaction().commit();
		
		return CategorysList;
	}
	@Override
	public Category getCategoryById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		session.beginTransaction();
		Category category = (Category) session.load(Category.class, Integer.valueOf(id));
		session.getTransaction().commit();
		//logger.info("Category loaded successfully, Category details="+category);
		return category;
	}

	@Override
	public void removeCategory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Category category = (Category) session.load(Category.class, Integer.valueOf(id));
		
		if(null != category){
			session.delete(category);
		}
		session.getTransaction().commit();
		logger.info("Category deleted successfully, Category details="+category);
	}
}
