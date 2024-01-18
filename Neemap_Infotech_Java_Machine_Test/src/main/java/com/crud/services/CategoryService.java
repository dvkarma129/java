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
import com.crud.repositories.CategoryRepository;
import com.crud.responsewrappers.AllResponseWrapper;

@Service
public class CategoryService {

	@Autowired        // Used to inject all property of other class 
	CategoryRepository categoryRepository;
	
	
	public Page<Category> getAllCategory(int page ,int pagesize) // Code to Fetch Data
	{
	  Pageable	pageable=PageRequest.of(page -1, pagesize);
	  return categoryRepository.findAll(pageable);
	}
	
	
	public ResponseEntity<?> addNewCategory(Category category)  // Code to Post Data
	{
		Category data=categoryRepository.save(category);
		if(data==null)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"There is no data in database please add some");
		}
		else
		{
			AllResponseWrapper arw=new AllResponseWrapper();
			arw.setData(data);
			arw.setMessage("Following Product Is Added.");
			return new ResponseEntity<>(arw,HttpStatus.ACCEPTED);
		}
	}
	
	
	public ResponseEntity<?> getCategoryById(int categoryid) // Code to Get Category of particular id
	{
         Category data=categoryRepository.findByCategoryid(categoryid).
        		       orElseThrow(()->{
        		    	             	throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with "+categoryid+" Not Exist.");
				                       }
			                      );
         AllResponseWrapper arw=new AllResponseWrapper();
         arw.setData(data);
         arw.setMessage("Category with "+categoryid+" Founded.");
         return new ResponseEntity<>(arw,HttpStatus.FOUND);
	}
	
	
	public ResponseEntity<?> updateCategory(Category category,int categoryid) // Code to Update Category of particular id
	{
		Category foundedCategory=categoryRepository.findByCategoryid(categoryid).orElseThrow(
				()->{
						throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with Id "+categoryid+" Not Exist.");
					}
				);
		category.setCategoryid(foundedCategory.getCategoryid());
		Category updated_category=categoryRepository.save(category);
		AllResponseWrapper arw=new AllResponseWrapper();
		arw.setData(updated_category);
		arw.setMessage("Category with Id "+categoryid+" Updated Sucessfully.");
		return new ResponseEntity<>(arw,HttpStatus.CREATED);
		
		
	}
	
	public ResponseEntity<?> deleteById(int categoryid) // Code to Delete Category of particular id
	{
		getCategoryById(categoryid);
		categoryRepository.deleteByCategoryid(categoryid);
		AllResponseWrapper arw=new AllResponseWrapper();
		arw.setData(" ");
		arw.setMessage("Category Deleted Sucessfully");
		return new ResponseEntity<>(arw,HttpStatus.ACCEPTED);
	}
}
