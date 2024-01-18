package com.empmngt.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empmngt.dto.EmpDto;
import com.empmngt.service.EmpService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/emp")
public class EmpController {

	private final EmpService empService;

	@GetMapping
	public ResponseEntity<List<EmpDto>> getAllEmp() {
		List<EmpDto> empList = empService.getAllEmp();
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	@GetMapping("/criteria/{salary}")
	public ResponseEntity<List<EmpDto>> getByCriteria(@PathVariable long salary) {
		List<EmpDto> empDto = empService.getByCriteria(salary);
		return new ResponseEntity<>(empDto, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<?> addEmp(@Valid @RequestBody EmpDto model, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        List<String> validationErrors = bindingResult.getFieldErrors().stream()
	                .map(error -> error.getDefaultMessage())
	                .collect(Collectors.toList());

	        return ResponseEntity.badRequest().body(Map.of("error", "Validation failed", "message", validationErrors));
	    }

	    if (empService.existsByPhoneNumberAndEmail(model.getPhoneNumber(), model.getEmail())) {
	        return ResponseEntity.badRequest().body(Map.of("error", "Duplicate email caught", "message",
	                "A User with the same email and contact already exists use another email or contact number"));
	    }

	    EmpDto empDto = empService.addEmp(model);
	    return ResponseEntity.ok(empDto);
	}

	@PostMapping("/addMul")
	public ResponseEntity<List<EmpDto>> addMulEmp(@RequestBody List<EmpDto> model) {
		List<EmpDto> empDto = empService.addMulEmp(model);
		return new ResponseEntity<>(empService.getAllEmp(), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpDto> getEmpById(@PathVariable long id) {
		EmpDto emp = empService.getEmpById(id);
		return new ResponseEntity<>(emp, HttpStatus.FOUND);
	}

	@PutMapping
	public ResponseEntity<EmpDto> updateEmp(@RequestBody EmpDto model) {
		EmpDto emp = empService.upadteEmp(model);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<List<EmpDto>> deleteEmp(@PathVariable long id) {
		empService.deleteEmp(id);
		return new ResponseEntity<>(empService.getAllEmp(), HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = empService.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=empService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

}
