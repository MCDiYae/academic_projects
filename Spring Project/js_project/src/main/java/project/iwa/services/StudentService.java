package project.iwa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.iwa.model.Student;
import project.iwa.repository.StudentRepository;
@Service
public class StudentService {
@Autowired StudentRepository studentRepository;
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
		
	}

}
