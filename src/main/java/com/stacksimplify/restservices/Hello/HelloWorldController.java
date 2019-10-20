package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method = RequestMethod.GET,path = "/helloworld")
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/userdetails")
	public UserDetails helloWorldBean() {
		return new UserDetails("Vishesh","Agarwal","Ajmer");
	}

	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Language", required=false) String locale){ 
		return messageSource.getMessage("label.hello",null,new Locale(locale));
	}
	
	@GetMapping("/hello-int1")
	public String getMessagesInI18NFormat1(){ 
		return messageSource.getMessage("label.hello",null,LocaleContextHolder.getLocale());
	}
	
	
}
