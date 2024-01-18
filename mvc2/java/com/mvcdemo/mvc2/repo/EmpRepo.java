package com.mvcdemo.mvc2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvcdemo.mvc2.dto.Emp;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Long> {

}
