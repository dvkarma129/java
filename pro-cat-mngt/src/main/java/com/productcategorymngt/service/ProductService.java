package com.productcategorymngt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productcategorymngt.dto.Category;
import com.productcategorymngt.dto.Product;
import com.productcategorymngt.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public Product saveProduct(Product product) {

		List<Category> managedCategories = new ArrayList<>();

		for (Category category : product.getCategories()) {
			Category managedCategory = categoryService.getCategoryById(category.getId());
			managedCategories.add(managedCategory);
		}

		product.setCategories(managedCategories);

		return productRepository.save(product);
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
	
	public Page<Product> getAllProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		return productRepository.findAll(pageable);
	}
}