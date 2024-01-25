package com.categorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsById(Category category);

	List<Category> findAllBySubCategoryAndParentCategory(String subCategory, String parentCategory);

//	@Query("SELECT distinct c.sub_category, c.parent_category, c.description, c.url FROM Category c")
//	List<Category> findUniqueCategories();

}
