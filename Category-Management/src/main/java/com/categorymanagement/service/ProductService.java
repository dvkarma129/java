package com.categorymanagement.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.categorymanagement.common.DuplicateFoundException;
import com.categorymanagement.common.ImageUtils;
import com.categorymanagement.common.NoSuchElementException;
import com.categorymanagement.common.NotFoundException;
import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;
import com.categorymanagement.repository.CategoryRepository;
import com.categorymanagement.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	public List<Products> getAllProducts() {
		return productRepository.findAll();
	}

	public Products getProductById(long id) {
		Optional<Products> products = productRepository.findById(id);
		if (products.isEmpty()) {
			throw new NoSuchElementException("Product with id " + id + " is not present");
		}
		return products.get();
	}

	
	public Products addProduct(Products product) {
	    Set<Category> updatedCategories = new HashSet<>();

	    if (product.getCategories() != null && !product.getCategories().isEmpty()) {
	        for (Category category : product.getCategories()) {
	            Category existingCategory = categoryService.getCategoryById(category.getId());

	            Category newCategory = new Category();
	            newCategory.setSubCategory(existingCategory.getSubCategory());
	            newCategory.setDescription(existingCategory.getDescription());
	            newCategory.setParentCategory(existingCategory.getParentCategory());
	            newCategory.setUrl(existingCategory.getUrl());

	            updatedCategories.add(newCategory);
	        }

	        product.setCategories(new ArrayList<>(updatedCategories));
	    } else {
	        product.setCategories(Collections.emptyList());
	    }

	    return productRepository.save(product);
	}


	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new NoSuchElementException("Product with id " + id + " is not present");
		}
		productRepository.deleteById(id);
	}

	public Products updateProduct(Long id, Products model) {
		Products existPro = getProductById(id);
		
		        existPro.setTitle(model.getTitle());
		        existPro.setDescription(model.getDescription());
		        existPro.setQuantity(model.getQuantity());
		        existPro.setPrice(model.getPrice());
		        existPro.setBrand(model.getBrand());
		        existPro.setSku(model.getSku());
		        existPro.setCategories(model.getCategories());
		
//		        for (int i = 0; i < existPro.getCategories().size(); i++) {
//		        	Category category = categoryService.getCategoryById(id);
//				}
		        
		        Products updatedProduct = addProduct(existPro);
		        
		        return updatedProduct;
	}

	public String uploadImage(MultipartFile file, Long pId) throws IOException {
		Optional<Products> products = productRepository.findById(pId);
		if (products.isEmpty()) {
			throw new NoSuchElementException("Product with id " + pId + " is not present");
		}
		products.get().setId(pId);
		Products products2 = Products.builder().image(ImageUtils.compressImage(file.getBytes())).build();
		products.get().setImage(products2.getImage());
		productRepository.save(products.get());
		return "Uploaded";
	}

	public byte[] downloadImage(Long pId) {
		Optional<Products> dbImageData = productRepository.findById(pId);
		if (dbImageData.isEmpty()) {
			throw new NoSuchElementException("Image of product with id " + pId + " is not present");
		}
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImage());
		return images;
	}
	

	public List<Products> saveMultipleProducts(List<Products> model) {		
		return productRepository.saveAll(model);
	}

	public void deleteAllPro() {
		productRepository.deleteAll();
	}
	
	public List<Products> findProduct(String proName) {
		
		List<Products> listPro =productRepository.findProductsLike(proName);
		if (listPro.isEmpty()) {
			throw new NoSuchElementException("no such types of product is present");
		}
		return listPro;
	}

	public Products findProductBySKU(String sku) {
		Products products = productRepository.findBySku(sku);
		if (products == null) {
			throw new NotFoundException("product with " + sku + " not found");
		}
		return products;
	}
}
