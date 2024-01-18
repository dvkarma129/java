package com.empmngt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empmngt.dto.DeptDto;
import com.empmngt.dto.EmpDto;

public 	interface DeptRepo extends JpaRepository<DeptDto, Long>{

	Optional<DeptDto> findByDepartmentName(String departmentName);

	boolean existsByDepartmentName(String departmentName);

}
