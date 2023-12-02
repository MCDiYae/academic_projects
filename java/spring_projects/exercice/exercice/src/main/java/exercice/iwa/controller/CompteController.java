package exercice.iwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exercice.iwa.model.Compte;
import exercice.iwa.service.CompteService;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    @Autowired
    private CompteService compteService;

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{numCompte}")
    public Compte getCompteByNumCompte(@PathVariable Long numCompte) {
        return compteService.getCompteByNumCompte(numCompte);
    }

    @GetMapping("/client/{clientCode}")
    public List<Compte> getComptesByClientCode(@PathVariable Long clientCode) {
        return compteService.getComptesByClientCode(clientCode);
    }

    @PostMapping
    public Compte addCompte(@RequestBody Compte compte) {
        return compteService.addCompte(compte);
    }

    @DeleteMapping("/{numCompte}")
    public void deleteCompte(@PathVariable Long numCompte) {
        compteService.deleteCompte(numCompte);
    }
    
}