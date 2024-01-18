package com.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Product {

	@Id   // Used to Indicate Primary Key
	@GeneratedValue(strategy = GenerationType.AUTO) // Used for Auto_Increment
	private int productid;
	
	
	@Column(unique = true,nullable = false) // Indicate Column will not have duplicate name and will not be empty
	@Size(min = 5, message = "Product Name Should be minimum 5 characters.")
	private String productname;
	
	
	@Column(nullable = false)
	@Min(value = 50,message = "Product Should be minimum 50 rs.")
	private double productprice;
	
	
	@Column(nullable = false)
	private String quantityperunit;
	
	
	@Column(nullable = false)
	private int productinstock;
	
	
	@ManyToOne    // Indicate many product have one category
	@JoinColumn(name="category_id",referencedColumnName = "categoryid")
	private Category category;


	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public double getProductprice() {
		return productprice;
	}


	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}


	public String getQuantityperunit() {
		return quantityperunit;
	}


	public void setQuantityperunit(String quantityperunit) {
		this.quantityperunit = quantityperunit;
	}


	public int getProductinstock() {
		return productinstock;
	}


	public void setProductinstock(int productinstock) {
		this.productinstock = productinstock;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	
	
	
	
	
	
}
