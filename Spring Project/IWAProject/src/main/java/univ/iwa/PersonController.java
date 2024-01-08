package univ.iwa;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.model.Person;

@RestController
public class PersonController {
	@PostMapping("persons")
	public String postPerson(@RequestBody Person person) {
		return person.GetNom();
	}

}
