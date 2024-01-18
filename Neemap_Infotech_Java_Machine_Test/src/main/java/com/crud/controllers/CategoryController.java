package com.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crud.models.Category;
import com.crud.services.CategoryService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping(" ") 
	public Page<Category> getAllCategory( @RequestParam (defaultValue = "1") int page ,@RequestParam (defaultValue = "3") int pagesize)
	{
	  return categoryService.getAllCategory(page, pagesize);
	}
	
	
	@PostMapping(" ") 
	public ResponseEntity<?> addNewCategory(@RequestBody @Valid Category category)
	{
		return categoryService.addNewCategory(category);
	}
	
	
	@GetMapping("/{categoryid}")
	public ResponseEntity<?> getCategoryById(@PathVariable int categoryid)
	{
       return categoryService.getCategoryById(categoryid);
	}
	
	
	@PutMapping("/{categoryid}")
	public ResponseEntity<?> updateCategory(@RequestBody @Valid Category category,@PathVariable int categoryid)
	{
		return categoryService.updateCategory(category, categoryid);
	}
	
	
	@DeleteMapping("/{categoryid}")
	public ResponseEntity<?> deleteById(@PathVariable int categoryid)
	{
		return categoryService.deleteById(categoryid);
	}
	
}
