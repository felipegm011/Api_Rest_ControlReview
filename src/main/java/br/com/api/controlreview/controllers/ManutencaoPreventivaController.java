package br.com.api.controlreview.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.controlreview.domain.ManutencaoPreventiva;
import br.com.api.controlreview.repository.ManutencaoPreventivaRepository;
import br.com.api.controlreview.service.ManutencaoPreventivaService;

@RestController
@RequestMapping("/servico")
public class ManutencaoPreventivaController {
	
	@Autowired
	ManutencaoPreventivaRepository preventivaRepository;
	@Autowired
	ManutencaoPreventivaService manutencaoService;
	
	@PostMapping("/manutencao_preventiva")
	private ResponseEntity<ManutencaoPreventiva> cadastrar(@RequestBody ManutencaoPreventiva manutencao){
		return ResponseEntity.ok(manutencaoService.cadastrar(manutencao));
	}
	
	@GetMapping("/manutencao_preventiva")
	private List<ManutencaoPreventiva> consulta(){
		return preventivaRepository.findAll();
	}
	
	@GetMapping("/manutencao_preventiva/{id}")
	private ResponseEntity<ManutencaoPreventiva> consultaId(@PathVariable Long id){
		
		Optional <ManutencaoPreventiva> manutencao = preventivaRepository.findById(id);
		
		if(manutencao.isPresent()) {
			return ResponseEntity.ok(manutencao.get()); 	
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}/finalizar_manutencao")
	public void finalizar(@PathVariable Long id) {
		manutencaoService.finalizar(id);
	}

}
