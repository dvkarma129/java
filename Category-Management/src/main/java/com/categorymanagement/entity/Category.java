package com.categorymanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "Category")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull(message = "subCategory cannot be null")
    @NotBlank(message = "subCategory required")
    @Size(max = 255, message = "subCategory must be less than or equal to 255 chars")
    private String subCategory;
    
	@NotNull(message = "parentCategory cannot be null")
    @NotBlank(message = "parentCategory required")
    @Size(max = 255, message = "parentCategory must be less than or equal to 255 chars")
    private String parentCategory;

	@NotNull(message = "description cannot be null")
    @NotBlank(message = "description required")
    @Size(max = 1000, message = "Description should be less than or equal to 1000 chars")
    private String description;

	@NotNull(message = "url cannot be null")
    @NotBlank(message = "url required")
    @Size(max = 255, message = "url link should be les then or equal to 255 chars")
    private String url;
}
