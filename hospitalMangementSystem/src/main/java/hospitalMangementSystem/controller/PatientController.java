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

import hospitalMangementSystem.Entity.Patient;
import hospitalMangementSystem.repo.PatientRepo;
import hospitalMangementSystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

	private PatientService patientService;

	@PostMapping("{deptId}")
	public ResponseEntity<?> addPatient(@RequestBody Patient model,@PathVariable Long deptId) throws Exception {
		Patient patient = patientService.addPatient(model,deptId);
		return new ResponseEntity<>(patient, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> PatientList = patientService.getAllPatients();
		return new ResponseEntity<>(PatientList, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getPatient(@PathVariable long id) throws Exception {
		Optional<Patient> Patient = patientService.getPatient(id);
		return new ResponseEntity<>(Patient, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> removePatient(@PathVariable long id) throws Exception {
		patientService.removePatient(id);
		return new ResponseEntity<>("Patient removed successfully", HttpStatus.OK);
	}
}
