package com.example.demo1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo1.dto.Employe;

public interface Repo extends JpaRepository<Employe, Integer>{

	List<Employe> findByTech(String empTech);

}
