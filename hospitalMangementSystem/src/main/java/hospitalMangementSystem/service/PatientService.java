
package hospitalMangementSystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hospitalMangementSystem.Entity.Patient;
import hospitalMangementSystem.Entity.Department;
import hospitalMangementSystem.Entity.Doctor;
import hospitalMangementSystem.Entity.History;
import hospitalMangementSystem.repo.PatientRepo;
import hospitalMangementSystem.repo.DepartmentRepo;
import hospitalMangementSystem.repo.DoctorRepo;
import hospitalMangementSystem.repo.HistoryRepo;
import hospitalMangementSystem.repo.PatientRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class PatientService {

	private PatientRepo patientRepo;
	private DoctorRepo doctorRepo;
	private DepartmentRepo departmentRepo;
	private HistoryRepo historyRepo;

	public Patient addPatient(Patient model, Long deptId) throws Exception {

		Patient newPatient = patientRepo.findByNameAndContact(model.getName(), model.getContact());
		if (newPatient == null) {
			newPatient = new Patient();
			Optional<Department> department = departmentRepo.findById(deptId);
			if (department.isEmpty()) {
				throw new Exception("Department not found");
			}
			newPatient.setDepartment(department.get());
			patientRepo.save(newPatient);

			newPatient.setName(model.getName());
			newPatient.setAge(model.getAge());
			newPatient.setGender(model.getGender());
			newPatient.setBloodGroup(model.getBloodGroup());
			newPatient.setAddress(model.getAddress());
			newPatient.setContact(model.getContact());
			
			History newHistory = new History();
			newHistory.setDateAdmitted(LocalDateTime.now());
			newHistory.setDepartment(departmentRepo.findById(deptId).get());
			newHistory.setPatient(newPatient);
			historyRepo.save(newHistory);
			patientRepo.save(newPatient);
		} else {

			Optional<Department> department = departmentRepo.findById(deptId);
			if (department.isEmpty()) {
				throw new Exception("Department not found");
			}
			newPatient.setDepartment(department.get());
			patientRepo.save(newPatient);
			
			History newHistory = new History();
			newHistory.setDateAdmitted(LocalDateTime.now());
			newHistory.setDepartment(departmentRepo.findById(deptId).get());
			newHistory.setPatient(newPatient);
			historyRepo.save(newHistory);
			patientRepo.save(newPatient);
		}
		
		
		return model;
	}

	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
	}

	public Optional<Patient> getPatient(long id) throws Exception {
		if (patientRepo.existsById(id)) {
			return patientRepo.findById(id);
		}
		throw new Exception("Patient not found with given id");
	}

	public void removePatient(long id) throws Exception {
		Optional<Patient> Patient = patientRepo.findById(id);
		if (Patient.isEmpty()) {
			throw new Exception("Patient not found with given id");
		}
		patientRepo.deleteById(id);
	}
}
