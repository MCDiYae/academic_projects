package exercice.iwa.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data @Entity
public class Compte {
	@Id
	Long numCompte;
	double solde;

	@ManyToOne 
	Client client;
}
