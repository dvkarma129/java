package com.productcategorymngt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcategorymngt.dto.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}