package project.iwa.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Student {
	@Id
	Long id;
	Date date_naissance;
	String nam;
	

}
