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

import br.com.api.controlreview.domain.HistoricoPeca;
import br.com.api.controlreview.repository.HistoricoPecaRepository;
import br.com.api.controlreview.service.HistoricoPecasService;

@RestController
@RequestMapping("/servico")
public class HistoricoPecasController {

	@Autowired
	HistoricoPecaRepository historicopecarepository;
	
	@Autowired
	HistoricoPecasService historicopecasservice;
	
	
	@PostMapping("/comentario")
	public ResponseEntity<HistoricoPeca> cadastrar(@RequestBody HistoricoPeca comentario){
		return ResponseEntity.ok(historicopecasservice.cadastro(comentario));
	}
	
	@PutMapping("/comentario/{id}")
	public ResponseEntity<HistoricoPeca> atualizar(@PathVariable Long id, @RequestBody HistoricoPeca comentario){
		return ResponseEntity.ok(historicopecasservice.atualiza(id, comentario));
	}
	
	@DeleteMapping("/comentario/{id}")
	public ResponseEntity<HistoricoPeca> deletar(@PathVariable Long id){
		
		if(!historicopecarepository.existsById(id)) {
			ResponseEntity.notFound().build();
		}
		historicopecasservice.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/comentario")
	public List<HistoricoPeca> listaComenatrios(){
		return historicopecarepository.findAll();
	}
	
	@GetMapping("/comentario/{id^}")
	public ResponseEntity<HistoricoPeca> listaId(@PathVariable Long id){
		Optional<HistoricoPeca> comentario = historicopecarepository.findById(id);
		
		if(comentario.isPresent()) {
			return ResponseEntity.ok(comentario.get());
		}
		return ResponseEntity.notFound().build();
	}
		
}
