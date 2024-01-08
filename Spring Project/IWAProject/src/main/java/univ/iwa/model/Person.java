package univ.iwa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor  @NoArgsConstructor
@Data
public class Person {
	
	Long id;
	String nom;
	int age;
	
	public String GetNom() {
		// TODO Auto-generated method stub
		return null;
	}

}
