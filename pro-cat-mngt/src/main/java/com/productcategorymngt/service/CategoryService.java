package com.productcategorymngt.service;
//CategoryService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcategorymngt.dto.Category;
import com.productcategorymngt.repo.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

 @Autowired
 private CategoryRepository categoryRepository;

 public List<Category> getAllCategories() {
     return categoryRepository.findAll();
 }

 public Category getCategoryById(Long categoryId) {
     return categoryRepository.findById(categoryId).orElse(null);
 }

 public Category saveCategory(Category category) {
     return categoryRepository.save(category);
 }

 public void deleteCategory(Long categoryId) {
     categoryRepository.deleteById(categoryId);
 }
}
