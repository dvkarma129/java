package com.categorymanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;
import com.categorymanagement.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //for get all categories
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() throws Exception {
    	List<Category> list = categoryService.getAllCategories();
        return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
    }
    
    //for get category by id
    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }

    //for add category
    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
    }

    //for delete category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //for update category
    @PostMapping("/update/{cId}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category,@PathVariable Long cId) {
        Category savedCategory = categoryService.updateCategory(category,cId);
        return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
    }
    
    //for delete category
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll() {
        categoryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //for saving multiple category
    @PostMapping("/saveMultiple")
    public ResponseEntity<List<Category>> saveMultiple(@Valid @RequestBody List<Category> model){
		List<Category> allSavedCate = categoryService.saveMultiple(model);
		return new ResponseEntity<>(allSavedCate,HttpStatus.OK);
	}

    //get DISTINCT categories
    @GetMapping("/getCategories")
    public ResponseEntity<List<Object[]>> getCategories() throws Exception {
    	List<Object[]> lis =  categoryService.getCategories();
        return new ResponseEntity<List<Object[]>>(lis,HttpStatus.OK);
    }
    
}