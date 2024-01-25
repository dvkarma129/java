package com.productcategorymngt.controller;

//ProductController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productcategorymngt.dto.Category;
import com.productcategorymngt.dto.Product;
import com.productcategorymngt.service.CategoryService;
import com.productcategorymngt.service.ProductService;

import jakarta.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		
		Product savedProduct = productService.saveProduct(product);
		return ResponseEntity.ok(savedProduct);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/page/{page}/size/{size}")
	public ResponseEntity<Page<Product>> getProductsByPage(@PathVariable int page, @PathVariable int size) {
	    Page<Product> productPage = productService.getAllProducts(page, size);
	    return ResponseEntity.ok(productPage);
	}

}
