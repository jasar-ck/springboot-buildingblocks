package com.hs.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-bean")
	public UserDetails hellowWorldBean() {
		return new UserDetails("John", "mike", "Paris");
	}

}
