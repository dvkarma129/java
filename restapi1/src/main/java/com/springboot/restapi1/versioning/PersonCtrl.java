package com.springboot.restapi1.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonCtrl {

	@GetMapping("/getPersonV1")
	public PersonV1 getP1() {
		return new PersonV1("Jhon wick");
	}
	//url versionig
	@GetMapping("/getPersonV2")
	public PersonV2 getP2() {
		return new PersonV2(new Name("Jhon","wick"));
	}
	
	//parameter versioning
	@GetMapping(path = "/p", params = "version=1")
	public PersonV1 getP1fromParameter() {
		return new PersonV1("picky chadda");
	}
	@GetMapping(path = "/p", params = "version=2")
	public PersonV2 getP2fromParameter() {
		return new PersonV2(new Name("Riky","chadda"));
	}
	
	//header versioning
	@GetMapping(path = "/p/header", headers = "X-API-VERSION=2")
	public PersonV2 getP2fromHeader() {
		return new PersonV2(new Name("piky","chadda"));
	}
	
	//media type 
	@GetMapping(path = "/p/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV2 getP2fromMedia() {
		return new PersonV2(new Name("poky","chadda"));
	}
	
	
	
}
