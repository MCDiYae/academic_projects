package univ.iwa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class test {
	@GetMapping("hello1")
	public String hello1() {
		return "bonjour ";
		
	}
	
	@GetMapping("hello2/{nom}")
	public String hello2(@RequestParam String nom) {
		return "bonjour "+nom;
		
	}

}
