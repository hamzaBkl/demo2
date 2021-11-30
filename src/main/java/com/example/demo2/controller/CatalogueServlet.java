package com.example.demo2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.model.Category;
import com.example.demo2.service.CategoryDAOImpl;
import com.example.demo2.view.CategoryForm;




@WebServlet("/adminCategory")
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CatalogueServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Créer le bean qui va stocker les données de la requête
				CategoryForm cf=new CategoryForm();
		//Faire appel au modèle pour ajouter une nouvelle catégorie
				CategoryDAO catal=new CategoryDAOImpl();
				if(request.getParameter("addCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setNameCat(getServletInfo());
					cf.setDescription(request.getParameter("description"));
		//Sauvegarder l'objet dans la base de données
					Category newCat = new Category();
					newCat.setTitle(request.getParameter("nomCat"));
					newCat.setDescription(request.getParameter("description"));
					catal.addCategory(newCat);
		//Récupérer toutes les catégries et les stocker dans le bean
					cf.setCategories(catal.listCategories());
				}
				else if(request.getParameter("idCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setIdCat(Long.parseLong(request.getParameter("idCat")));

		//Supprimer l'objet de la base de données
					catal.removeCategory((int)cf.getIdCat());
					cf.setCategories(null);
				}
				else if(request.getParameter("chercheCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setKeyWord(request.getParameter("motCle"));
		//Récupérer les catégries par mot clé et les stocker dans le bean
					cf.setCategories(catal.selectCatByKeyword(cf.getKeyWord()));
				}
				else{
		//Récupérer toutes les catégries et les stocker dans le bean
					cf.setCategories(catal.listCategories());
				}

		/* avant de donner la main la page JSP pour afficher
		 * enregitrer le bean dans la requête ou la session courante
		 */
					HttpSession session=request.getSession();
					session.setAttribute("catForm",cf);
		//Faire une redirection vers la vue JSP pour afficher.
					response.sendRedirect("Categorys.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
