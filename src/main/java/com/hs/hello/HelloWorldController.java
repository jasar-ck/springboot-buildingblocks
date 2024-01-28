package com.hs.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-bean")
	public UserDetails hellowWorldBean() {
		return new UserDetails("John", "mike", "Paris");
	}

	
	@GetMapping("/hello-in")
	public String helloi18n(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello",null,  new Locale(locale)) ;
	}
}
