package project.iwa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.iwa.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
