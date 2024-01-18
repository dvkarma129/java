package hospitalMangementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospitalMangementSystem.Entity.Department;
import hospitalMangementSystem.repo.DepartmentRepo;
import hospitalMangementSystem.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("department")
public class DepartmentController {

	private DepartmentService departmentService; 
	
	@PostMapping
	public ResponseEntity<?> addDepartment(@RequestBody Department model) throws Exception{
		Department Department = departmentService.addDepartment(model);
		return new ResponseEntity<>(Department,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments(){
		List<Department> DepartmentList = departmentService.getAllDepartments();
		return new ResponseEntity<>(DepartmentList,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getDepartment(@PathVariable long id) throws Exception{
		Optional<Department> Department = departmentService.getDepartment(id);
		return new ResponseEntity<>(Department,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeDepartment(@PathVariable long id) throws Exception{
		departmentService.removeDepartment(id);
		return new ResponseEntity<>("Department removed successfully",HttpStatus.OK);
	}
}
