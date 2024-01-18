package com.empmngt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empmngt.dto.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}