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

import br.com.api.controlreview.domain.OrdemServicoReview;
import br.com.api.controlreview.repository.OrdemServicoRepository;
import br.com.api.controlreview.service.OrdemServicoService;

@RestController
@RequestMapping("/servico")
public class OrdemServicoController {
	
	@Autowired
	OrdemServicoRepository osRepository;
	
	@Autowired
	OrdemServicoService osService;
	
	@PostMapping("/ordemservico")
	public ResponseEntity<OrdemServicoReview> cadastrar(@RequestBody OrdemServicoReview os){
		return ResponseEntity.ok(osService.cadastrar(os));
	}
	
	@PutMapping("/ordemservico/{id}")
	public ResponseEntity<OrdemServicoReview> atualizarOs(@PathVariable Long id, @RequestBody OrdemServicoReview os){
		if(!osRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		os = osService.atualiza(id, os);
		return ResponseEntity.ok(os);
	}
	
	@PutMapping("/{id}/finalizar")
	public void finalizar(@PathVariable Long id) {
		osService.finalizar(id);
	}
	
	@PutMapping("/{id}/cancelar")
	public void cancelar(@PathVariable Long id) {
		osService.cancelar(id);
	}
	
	@DeleteMapping("/ordemservico/{id}")
	public ResponseEntity<OrdemServicoReview> deletarOs(@PathVariable Long id){
		if(!osRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		osService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/ordemservico")
	public List<OrdemServicoReview> listarOrdemServico(){
		return osRepository.findAll(); 
	}
	
	@GetMapping("/ordemservico/{id}")	 	 	
	public ResponseEntity<OrdemServicoReview> listarOrdemServicoId(@PathVariable Long id){
		Optional<OrdemServicoReview> os = osRepository.findById(id);
		
		if(os.isPresent()) {
			return ResponseEntity.ok(os.get());
		}
		return ResponseEntity.notFound().build();
	}
	
}
