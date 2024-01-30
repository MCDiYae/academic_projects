package project.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.iwa.model.Student;
import project.iwa.services.StudentService;

@RestController
@CrossOrigin (origins = "*")
public class StudentController {
	@Autowired StudentService studentService;
	@GetMapping("students")
	public List<Student> getAllStudent(){
		return studentService.getAllStudent();	
	}
	@PostMapping("students")
	public Student addStudent(@RequestBody Student student){
		return studentService.addStudent(student);	
	}
	@DeleteMapping("students/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		}

}
