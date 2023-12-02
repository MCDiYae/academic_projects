package exercice.iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercice.iwa.model.Client;
import exercice.iwa.model.Compte;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
	

}
