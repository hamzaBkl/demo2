package com.example.demo2.controller;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.dao.ProductDAO;
import com.example.demo2.model.Category;
import com.example.demo2.model.Product;
import com.example.demo2.service.CategoryDAOImpl;
import com.example.demo2.service.ProductDAOImpl;
import com.example.demo2.view.ProductForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/adminProduct")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ProductServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				ProductForm form=new ProductForm();

				ProductDAO dao=new ProductDAOImpl();
				CategoryDAO categoryDAO=new CategoryDAOImpl();
				form.setCategories(categoryDAO.listCategories());

		if(request.getParameter("addProduct")!=null){

					form.setDesignation(request.getParameter("designation"));
					form.setSelected(false);
					form.setPrice(Double.parseDouble(request.getParameter("price")));
					form.setPhoto(request.getParameter("designation").trim()+".png");
					form.setQuantity(Integer.parseInt(request.getParameter("quantity")));
					Category category =categoryDAO.getCategoryById(Long.parseLong(request.getParameter("idCat")));
					form.setCategory(category);

					Product newProduct = new Product();
					newProduct.setDesignation(request.getParameter("designation"));
					newProduct.isSelected(false);
					newProduct.setPrice(Double.parseDouble(request.getParameter("price")));
					//newProduct.setPrice(Double.parseDouble(request.getParameter("price")));
					newProduct.setPhoto(request.getParameter("designation").trim()+".png");
					newProduct.setQuantity(Integer.parseInt(request.getParameter("quantity")));
					newProduct.setCategory(category) ;
					dao.addProduct(newProduct);

					form.setProducts(dao.listProducts());
				}
				else if(request.getParameter("idProduct")!=null){

			form.setIdProduct(Long.parseLong(request.getParameter("idProduct")));

					dao.removeProduct(form.getIdProduct());
					form.setProducts(dao.listProducts());
				}
				else if(request.getParameter("searchProduct")!=null){

					form.setKeyWord(request.getParameter("keyWord"));
					form.setIdCat(Long.parseLong(request.getParameter("idCat")));

					form.setProducts(dao.searchProduct(form.getKeyWord(),form.getIdCat()));
				}
				else{
					form.setProducts(dao.listProducts());

		}


					HttpSession session=request.getSession();
					session.setAttribute("productForm",form);
					response.sendRedirect("admin_products.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
