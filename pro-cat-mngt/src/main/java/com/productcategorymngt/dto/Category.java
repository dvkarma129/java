package com.productcategorymngt.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "sub Category cannot be blank")
    @Size(max = 255, message = "Name must be less than or equal to 255 characters")
    private String subCategory;
    
    @NotBlank(message = "parent Category cannot be blank")
    @Size(max = 255, message = "Name must be less than or equal to 255 characters")
    private String parentCategory;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

    @NotBlank(message = "URL cannot be blank")
    @Size(max = 255, message = "URL must be less than or equal to 255 characters")
    private String url;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;


}
