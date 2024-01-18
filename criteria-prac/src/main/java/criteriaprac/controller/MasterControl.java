package criteriaprac.controller;

import java.util.List;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import criteriaprac.dto.Student;
import criteriaprac.repo.StudentRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/criteria")
public class MasterControl {

	@Autowired
	private StudentRepo studentRepo;

	@GetMapping
	public ResponseEntity< List<Student>> getAllStudents(){
		return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Student> newStudent(@Valid @RequestBody Student model) {
		return new ResponseEntity<>(studentRepo.save(model),HttpStatus.CREATED);
	}

}
