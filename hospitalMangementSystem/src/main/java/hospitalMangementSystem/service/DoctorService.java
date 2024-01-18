
package hospitalMangementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hospitalMangementSystem.Entity.Department;
import hospitalMangementSystem.Entity.Doctor;
import hospitalMangementSystem.repo.DepartmentRepo;
import hospitalMangementSystem.repo.DoctorRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorService {

	private DoctorRepo doctorRepo;
	private DepartmentRepo departmentRepo;

	public Doctor addDoctor(Doctor model, long deptId) throws Exception {
		Department department =departmentRepo.findById(deptId).get();
		if (department == null) {
			throw new Exception("department not exists");
		}
		model.setDepartment(department);
		return doctorRepo.save(model);
	}

	public List<Doctor> getAllDoctors() {
		return doctorRepo.findAll();
	}

	public void removeDoctor(long id) throws Exception {
		Optional<Doctor> doctor =  doctorRepo.findById(id);
		if (doctor.isEmpty()) {
			throw new Exception("docotor not found with given id");
		}
		doctorRepo.deleteById(id);
		
	}

	public Optional<Doctor> getDoctor(long id) throws Exception{
		if (!doctorRepo.existsById(id)) {
			throw new Exception("docotor not found with given id");
		}
		return doctorRepo.findById(id);
	}

	public Doctor updateDoctor(Doctor model, long deptId) throws Exception {
		Department department =departmentRepo.findById(deptId).get();
		if (department == null) {
			throw new Exception("department not exists");
		}
		model.setDepartment(department);
		return doctorRepo.save(model);
	}
}
