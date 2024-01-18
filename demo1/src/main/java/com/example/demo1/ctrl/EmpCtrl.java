package com.example.demo1.ctrl;


import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dto.Employe;
import com.example.demo1.repo.Repo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class EmpCtrl {

	private final Repo repo;

	@PostMapping
	public String add(@RequestBody @Valid Employe employe) {
		Employe e = new Employe();
		e.setName(employe.getName());
		e.setEmail(employe.getEmail());
		e.setTech(employe.getTech());
		repo.save(e);
		return "added sucessfully";
	}

	@GetMapping("/view")
	public List<Employe> view() {
		List<Employe> el = (List<Employe>) repo.findAll();
		return el;
	}

	@GetMapping("/viewById/{id}")
	public Employe viewById(@PathVariable("id") int empId) {
		Employe el = repo.findById(empId).get();
		return el;
	}

	@GetMapping("/getByTech/{tech}")
	public List<Employe> getByTech(@PathVariable("tech") String empTech) {
		List<Employe> el = repo.findByTech(empTech);
		return el;
	}

	@DeleteMapping("{id}")
	public String delEmp(@PathVariable("id") int empId) {
		repo.deleteById(empId);
		return "deleted";
	}

	@PutMapping("{id}")
	public String upEmp(@PathVariable("id") int empId, @RequestBody Employe employe) {
		Employe e = repo.findById(empId).get();
		e.setName(employe.getName());
		e.setEmail(employe.getEmail());
		e.setTech(employe.getTech());
		repo.save(e);
		return "Updated";
	}

}
