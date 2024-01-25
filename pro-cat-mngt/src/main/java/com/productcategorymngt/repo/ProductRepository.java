package com.productcategorymngt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcategorymngt.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}