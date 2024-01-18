package com.empmngt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmngt.dto.DeptDto;
import com.empmngt.dto.EmpDto;
import com.empmngt.repo.DeptRepo;
import com.empmngt.repo.EmpRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeptService {

	@Autowired
	private final DeptRepo deptRepo;
	private final EmpRepo empRepo;


	public List<DeptDto> getAllDept() {
		return deptRepo.findAll();
	}

	@Transactional
	public DeptDto addDept(DeptDto model) {
	    model.getEmpDtoList().forEach(empDto -> empDto.setDepartment(model));
	    empRepo.saveAll(model.getEmpDtoList());
	    return deptRepo.save(model);
	}
	
	public boolean existsByName(String departmentName) {
		return deptRepo.existsByDepartmentName(departmentName);
	}
	
	public void addMulDeptByFL(List<DeptDto> model) {
		for (int i = 0; i < model.size(); i++) {
			DeptDto dept = model.get(i);
			deptRepo.save(dept);
		}
	}

	public Optional<DeptDto> getDeptById(Long id) {
		return deptRepo.findById(id);
	}

	public DeptDto upadteDept(DeptDto model) {
		return deptRepo.save(model);
	}

	public void deleteDept(long id) {
		deptRepo.deleteById(id);
	}

	public List<DeptDto> addMulDept(List<DeptDto> model) {
		return deptRepo.saveAll(model);
	}

	public Optional<DeptDto> getDeptByName(String departmentName) {
		return deptRepo.findByDepartmentName(departmentName);
	}

	public void delMulDept(List<DeptDto> model) {
		deptRepo.deleteAll(model);
	}

	public void delMulDeptByFL(List<DeptDto> model) {
		for (int i = 0; i < model.size(); i++) {
			DeptDto dept = model.get(i);
			deptRepo.deleteById(dept.getId());

		}
	}

	public List<EmpDto> getEmployeesInDepartmentWithName(String departmentName, String name) {
		return empRepo.findEmployeesByDepartmentAndNameLike(departmentName, name);
	}


}
