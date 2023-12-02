package exercice.iwa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercice.iwa.model.Compte;
@Repository
public interface CompteRepository extends JpaRepository<Compte,Long>{

	List<Compte> findByClientCode(Long clientCode);

	Optional<Compte> findByNumCompte(Long numCompte);
	

}
