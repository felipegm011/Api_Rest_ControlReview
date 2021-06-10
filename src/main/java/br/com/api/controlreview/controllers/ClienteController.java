package br.com.api.controlreview.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.controlreview.domain.ClienteReview;
import br.com.api.controlreview.repository.ClienteRepository;
import br.com.api.controlreview.service.ClienteReviewService;

@RestController
@RequestMapping("/servico")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteReviewService serviceCliente;

	@GetMapping("/cliente")
	public List<ClienteReview> retornaCliente() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteReview> retornaClienteId(@PathVariable Long id){
		Optional<ClienteReview> cliente = clienteRepository.findById(id);

		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<ClienteReview> cadastroCliente(@RequestBody ClienteReview cliente){
		ClienteReview dados = serviceCliente.cadastro(cliente);
		return ResponseEntity.ok(dados);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<ClienteReview> atualizaCliente(@PathVariable Long id, @RequestBody ClienteReview cliente ){
		
		// verifica se o id passado existe
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		//pegando a instancia do cliente selecionado para atualização do cadastro e atualizando
		//cliente.setId(id);
		//cliente = clienteRepository.save(cliente);
		
		cliente = serviceCliente.atualiza(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<ClienteReview> deletaCliente(@PathVariable Long id, @RequestBody ClienteReview cliente){
		
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		serviceCliente.deletar(id);
		return ResponseEntity.noContent().build();
	}

	
}
