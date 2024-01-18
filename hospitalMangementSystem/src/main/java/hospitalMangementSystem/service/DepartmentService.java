
package hospitalMangementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hospitalMangementSystem.Entity.Department;
import hospitalMangementSystem.repo.DepartmentRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {

	private DepartmentRepo departmentRepo;

	public Department addDepartment(Department model) throws Exception {

		if (departmentRepo.existsByName(model.getName())) {
			throw new Exception("Department already exists");
		}
		return departmentRepo.save(model);
	}

	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}
	
	public Optional<Department> getDepartment(long id) throws Exception{
		if (departmentRepo.existsById(id)) {
			return departmentRepo.findById(id);
		}
		throw new Exception("department not found with given id");
	}

	public void removeDepartment(long id) throws Exception {
		Optional<Department> Department = departmentRepo.findById(id);
		if (Department.isEmpty()) {
			throw new Exception("Department not found with given id");
		}
		departmentRepo.deleteById(id);
	}

}
