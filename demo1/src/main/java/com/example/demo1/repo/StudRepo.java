package com.example.demo1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo1.dto.Employe;
import com.example.demo1.dto.Student;

@RepositoryRestResource
public interface StudRepo extends JpaRepository<Student, Integer>{


}
