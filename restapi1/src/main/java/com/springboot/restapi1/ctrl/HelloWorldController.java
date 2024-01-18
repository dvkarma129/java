package com.springboot.restapi1.ctrl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {

	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping(path ="/hello-world-bean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("hello world");
	}
	
	@GetMapping(path ="/hello-world/path-variable/{name}")
	public HelloWorld helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorld("hello world" + name);
	}
	
}

