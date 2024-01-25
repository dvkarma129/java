package com.categorymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.categorymanagement.common.NoSuchElementException;
import com.categorymanagement.common.NotFoundException;
import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;
import com.categorymanagement.repository.CategoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CategoryService {
	@Autowired
	EntityManager entityManager;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() throws Exception{
		return categoryRepository.findAll();
	}

	public Category getCategoryById(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isEmpty()) {
			throw new NoSuchElementException("Category with id " + categoryId + " not found");
		}
		return category.get();
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category deleteCategory(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isEmpty()) {
			throw new NoSuchElementException("Category with id " + categoryId + " not found");
		}
		categoryRepository.deleteById(categoryId);
		List<Category> listCat = categoryRepository.findAllBySubCategoryAndParentCategory(category.get().getSubCategory(),
												category.get().getParentCategory());
		deleteAll(listCat);
		return category.get();
	}

	public Category updateCategory(Category model, Long cId) {
		Optional<Category> category = categoryRepository.findById(cId);
		if (category.isEmpty()) {
			throw new NoSuchElementException("Category with id " + cId + " not found");
		}
		model.setId(category.get().getId());
		List<Category> listCat = categoryRepository.findAllBySubCategoryAndParentCategory(category.get().getSubCategory(),
													category.get().getParentCategory());
		
		for (int i = 0; i < listCat.size(); i++) {
			Category existCate = getCategoryById(listCat.get(i).getId());
			existCate.setSubCategory(model.getSubCategory());
			existCate.setDescription(model.getDescription());
			existCate.setParentCategory(model.getParentCategory());
			existCate.setUrl(model.getUrl());
			categoryRepository.save(existCate);
		}
		
		return categoryRepository.save(model);
	}

	public void deleteAll(List<Category> listCat) {
		categoryRepository.deleteAll();
	}
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	public List<Category> saveMultiple(List<Category> model) {
		 return categoryRepository.saveAll(model);
	}

	public List<Object[]> getCategories () {
		Query query=entityManager.createQuery("SELECT distinct subCategory, parentCategory, description, url FROM Category ");
		return query.getResultList();
	}
}
