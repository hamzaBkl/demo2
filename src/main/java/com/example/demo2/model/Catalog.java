package com.example.demo2.model;


import com.example.demo2.Utilitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Catalog {


    public Catalog() {

    }

    public void addCategory(String title, String description){
        Connection connection= Utilitaire.getConnection();
        String query ="insert into category(nameCategory,description) values(?,?) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void delCategory(Long idCat){
        Connection connection= Utilitaire.getConnection();
        String query ="delete from category where idCategory= ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,idCat);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Category> selectAllCat(){
        String query ="select * from category";
        return selectCategories(query);
    }

    public  ArrayList<Category> selectCatByKeyword(String keyword){
        String query ="select * from category where nameCategory like '%"+keyword+"%'";
        return selectCategories(query);
    }


    private  ArrayList<Category> selectCategories(String query){
        Connection connection= Utilitaire.getConnection();
        ArrayList<Category> categories=new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                Category category=new Category();
                category.setIdCat(resultSet.getLong("idCategory"));
                category.setNameCat(resultSet.getString("nameCategory"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void main(String[] args) {
//        List<Category> categories=selectAllCat();
//        for (Category category:categories){
//            System.out.println(category.getIdCat()+" - "+category.getTitle()+" : "+category.getDescription());
//        }

    }
}
