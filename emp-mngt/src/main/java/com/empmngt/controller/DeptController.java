package com.empmngt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empmngt.dto.DeptDto;
import com.empmngt.dto.EmpDto;
import com.empmngt.exceptions.NotFoundExceptions;
import com.empmngt.exceptions.ValidationException;
import com.empmngt.service.DeptService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/dept")
public class DeptController {

	private final DeptService deptService;

	@GetMapping
	public ResponseEntity<List<DeptDto>> getAllDept() {
		List<DeptDto> deptList = deptService.getAllDept();
		return new ResponseEntity<>(deptList, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getDeptById(@PathVariable long id) {

		Optional<DeptDto> dept = deptService.getDeptById(id);
		if (!dept.isPresent()) {
			throw new NotFoundExceptions("Department not found");
		}
		return new ResponseEntity<>(dept, HttpStatus.OK);
	}

	@GetMapping("{departmentName}")
	public ResponseEntity<?> getDeptByName(@PathVariable String departmentName) {
		departmentName.equalsIgnoreCase(departmentName);
		Optional<DeptDto> dept = deptService.getDeptByName(departmentName);
		if (!dept.isPresent()) {
			throw new NotFoundExceptions("Department not found");
		}
		return new ResponseEntity<>(dept, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addDept(@Valid @RequestBody DeptDto model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> validationErrors = bindingResult.getFieldErrors()
					.stream()
					.map(error -> error
					.getDefaultMessage())
					.collect(Collectors.toList());

			return new ResponseEntity<>(Map.of("error", "Validation failed", "message", validationErrors),
					HttpStatus.BAD_REQUEST);
		}
		if (deptService.existsByName(model.getDepartmentName())) {
	        return new ResponseEntity<>(Map.of("error", "Duplicate department", "message", "A department with the same name already exists"),
	                HttpStatus.BAD_REQUEST);
	    }
		DeptDto deptDto = deptService.addDept(model);
		return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
	}

	@PostMapping("/addMul")
	public ResponseEntity<List<DeptDto>> addMulDept(@RequestBody List<DeptDto> model) {
		deptService.addMulDept(model);
		return new ResponseEntity<>(deptService.getAllDept(), HttpStatus.CREATED);
	}

	@PostMapping("/addMulByFL")
	public ResponseEntity<List<DeptDto>> addMulDeptByFL(@RequestBody List<DeptDto> model) {
		deptService.addMulDeptByFL(model);
		return new ResponseEntity<>(deptService.getAllDept(), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<DeptDto> updateDept(@RequestBody DeptDto model) {
		DeptDto dept = deptService.upadteDept(model);
		return new ResponseEntity<>(dept, HttpStatus.OK);
	}

	@DeleteMapping("/delMul")
	public ResponseEntity<?> delMulDept(@RequestBody List<DeptDto> model) {
		deptService.delMulDept(model);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<List<DeptDto>> deleteDept(@PathVariable long id) {
		deptService.deleteDept(id);
		return new ResponseEntity<>(deptService.getAllDept(), HttpStatus.OK);
	}

	@DeleteMapping("/delMulByFL")
	public ResponseEntity<?> delMulDeptByFL(@RequestBody List<DeptDto> model) {
		deptService.delMulDeptByFL(model);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/search/{departmentName}/{name}")
    public ResponseEntity<List<EmpDto>> getEmployeesInDepartmentWithName(@PathVariable String departmentName,@PathVariable String name) {
        List<EmpDto> employees = deptService.getEmployeesInDepartmentWithName(departmentName,name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	
}
