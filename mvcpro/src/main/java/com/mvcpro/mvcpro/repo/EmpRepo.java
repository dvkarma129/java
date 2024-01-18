package com.mvcpro.mvcpro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvcpro.mvcpro.dto.Emp;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Long> {

}