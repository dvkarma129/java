package com.categorymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

	Products findByTitleAndBrand(String title, String brand);

	@Query("SELECT p FROM Products p WHERE p.title LIKE %:proName%")
	List<Products> findProductsLike(@Param("proName") String proName);

	Products findBySku(String sku);

}
