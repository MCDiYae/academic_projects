package project.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.iwa.model.Student;
import project.iwa.services.StudentService;

@RestController
@CrossOrigin ("*")
public class StudentController {
	@Autowired StudentService studentService;
	@GetMapping("student")
	public List<Student> getAllStudent(){
		return studentService.getAllStudent();
		
	}
	@PostMapping("student")
	public Student addStudent(@RequestBody Student student){
		return studentService.addStudent(student);
		
	}

}
