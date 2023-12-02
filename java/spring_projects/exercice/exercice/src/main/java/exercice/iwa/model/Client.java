package exercice.iwa.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data @Entity
public class Client {
	@Id
	 Long code;
     String nom;
     String prenom;
     Date dateNaissance;
     @OneToMany (mappedBy ="client")
     List<Compte>comptes;
    

}
