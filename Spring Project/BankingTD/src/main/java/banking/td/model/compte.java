package banking.td.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
public class compte {
	@Id
	Long numCompte;
	double solde;
	

}
