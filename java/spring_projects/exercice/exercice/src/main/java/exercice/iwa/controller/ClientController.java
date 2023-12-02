package exercice.iwa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exercice.iwa.model.Client;
import exercice.iwa.model.Compte;
import exercice.iwa.service.ClientService;

@RestController
public class ClientController {
	@Autowired ClientService clientService ;
	@PostMapping("clients")
	public Client addClient(@RequestBody Client client) {
		return clientService.addClient(client);
	}
	
//@PostMapping("clients/{code}/comptes")
//public Compte addCompteToClient(@PathVariable Long code, @RequestBody Compte compte ) {
//	Optional <Client> client=clientService.getClientByCode(code);
//	if (client !=null) {
//		compte.setClient(Client.get());
//		compteService.
//	}
//}
	 @GetMapping
	    public List<Client> getAllClients() {
	        return clientService.getAllClients();
	    }

	    @GetMapping("/{code}")
	    public Object getClientByCode(@PathVariable Long code) {
	        return clientService.getClientByCode(code);
	    }

	    

	    @DeleteMapping("/{code}")
	    public void deleteClient(@PathVariable Long code) {
	        clientService.deleteClient(code);
	    }
}
