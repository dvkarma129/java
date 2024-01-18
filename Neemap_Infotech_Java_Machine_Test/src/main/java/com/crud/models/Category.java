package com.crud.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;



@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryid;
	
	@Column(nullable = false)
	@Size(min=5,max=20,message="Category Name Should Be Between 5-100 character ")
	private String categoryname;
	
	@Column(nullable = false)
	@Size(min=5,max=100,message="Description Should Be Between 5-100 character ")
	private String description;
	
	
	@OneToMany(mappedBy ="category" )
	@JsonIgnore // Used When we Fetch Data from Get Method It will not show particular Column name.
	private List<Product> product;


	public int getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


	public String getCategoryname() {
		return categoryname;
	}


	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	


	
	
	
	
}
