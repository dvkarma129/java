package com.productcategorymngt.controller;

//CategoryController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productcategorymngt.dto.Category;
import com.productcategorymngt.service.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(category);
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		Category savedCategory = categoryService.saveCategory(category);
		return ResponseEntity.ok(savedCategory);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok().build();
	}
}
