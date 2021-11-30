package com.example.demo2.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;
import java.util.List;

@Entity
@Table(name="category")
@NamedQuery(query = "SELECT c FROM Category c where c.id = :id",name = "find category by id")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
@Proxy(lazy = false)

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCat;
	
    private String title;
    private String description;

	@OneToMany(mappedBy="category")
	private List<Product> products;
	
    public Category() {
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Product addProduit(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduit(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}
	
}
