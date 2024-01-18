package com.crud.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crud.models.Category;
import com.crud.models.Product;
import com.crud.repositories.CategoryRepository;
import com.crud.repositories.ProductRepository;
import com.crud.responsewrappers.AllResponseWrapper;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Page<Product> getAllProducts(int page,int pagesize)
	{
		Pageable data=PageRequest.of(page -1, pagesize);
		return productRepository.findAll(data);
	}
	
	public ResponseEntity<?> addProduct(Product product)
	{
		String category_found=product.getCategory().getCategoryname();
		Category category=categoryRepository.findByCategoryname(category_found).orElseThrow(()->{
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no Category with name "+category_found);
		});
		product.setCategory(category);
	    Product newproduct=productRepository.save(product);
		if(newproduct==null)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"There is some probe in server.");
		}
		else
		{
			AllResponseWrapper arw=new AllResponseWrapper();
			arw.setData(newproduct);
			arw.setMessage("Product Added Sucessfully.");
			return new ResponseEntity<>(arw,HttpStatus.CREATED);
		}
		
	}
	
	
	public ResponseEntity<?> getProductById(int productid)
	{
		Product productFound=productRepository.findByProductid(productid).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product With Id "+productid+" not Exist.");
		});
		AllResponseWrapper arw=new AllResponseWrapper();
		arw.setData(productFound);
		arw.setMessage("Product With Id "+productid+" Exist.");
		return new ResponseEntity<>(arw,HttpStatus.FOUND);
	}
	
	
	public ResponseEntity<?> updateById(Product product,int productid)
	{
		getProductById(productid);
		product.setProductid(productid);
		String category_found=product.getCategory().getCategoryname();
		Category category=categoryRepository.findByCategoryname(category_found).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not category with name "+category_found);
		});
		product.setCategory(category);
		Product updatedProduct=productRepository.save(product);
		AllResponseWrapper arw=new AllResponseWrapper();
		arw.setData(updatedProduct);
		arw.setMessage("Product With Id "+productid+" updated sucessfully");
		return new ResponseEntity<>(arw,HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<?> deleteById(int productid)
	{
		getProductById(productid);
		productRepository.deleteByProductid(productid);
		AllResponseWrapper arw=new AllResponseWrapper();
		arw.setData(" ");
		arw.setMessage("Product With Id "+productid+" Deleted");
		return new ResponseEntity<>(arw,HttpStatus.OK);
	}
}
