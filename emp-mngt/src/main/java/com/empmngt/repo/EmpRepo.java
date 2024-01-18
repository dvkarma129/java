package com.empmngt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empmngt.dto.EmpDto;

public 	interface EmpRepo extends JpaRepository<EmpDto, Long>{

	
	@Query("SELECT e FROM EmpDto e WHERE e.department.departmentName = :departmentName AND (e.firstName LIKE (%:name%) OR e.lastName LIKE (%:name%))")
    List<EmpDto> findEmployeesByDepartmentAndNameLike(
            @Param("departmentName") String departmentName,
            @Param("name") String name
    );

	boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);

}
