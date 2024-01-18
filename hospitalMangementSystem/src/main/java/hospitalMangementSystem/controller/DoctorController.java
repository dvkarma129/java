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

import hospitalMangementSystem.Entity.Doctor;
import hospitalMangementSystem.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

	private DoctorService doctorService;
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		List<Doctor> doctorList = doctorService.getAllDoctors();
		return new ResponseEntity<>(doctorList,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getDoctor(@PathVariable long id) throws Exception{
		Optional<Doctor> doctor = doctorService.getDoctor(id);
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	
	@PostMapping("{deptId}")
	public ResponseEntity<?> addDoctor(@RequestBody Doctor model, @PathVariable long deptId) throws Exception{
		Doctor doctor = doctorService.addDoctor(model, deptId);
		return new ResponseEntity<>(doctor,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeDoctor(@PathVariable long id) throws Exception{
		doctorService.removeDoctor(id);
		return new ResponseEntity<>("Doctor deleted successfully",HttpStatus.OK);
	}
	
	@PostMapping("update/{deptId}")
	public ResponseEntity<?> updateDoctor(@RequestBody Doctor model, @PathVariable long deptId) throws Exception{
		Doctor doctor = doctorService.updateDoctor(model, deptId);
		return new ResponseEntity<>(doctor,HttpStatus.CREATED);
	}
}
