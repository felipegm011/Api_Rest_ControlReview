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

import br.com.api.controlreview.domain.Veiculo;
import br.com.api.controlreview.repository.VeiculoRepository;
import br.com.api.controlreview.service.VeiculoService;

@RestController
@RequestMapping("/servico")
public class VeiculoController {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	VeiculoService veiculoService;
	
	@GetMapping("/veiculo")
	public List<Veiculo> listarVeiculo(){
		return veiculoRepository.findAll(); 
	}
	
	@GetMapping("/veiculo/{id}")
	public ResponseEntity<Veiculo> listarVeiculoId(@PathVariable Long id){
		Optional<Veiculo> veiculos = veiculoRepository.findById(id);
		
		if(veiculos.isPresent()) {
			return ResponseEntity.ok(veiculos.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/veiculo")
	public ResponseEntity<Veiculo> cadastroCliente(@RequestBody Veiculo veiculo){		
		return ResponseEntity.ok(veiculoService.cadastrar(veiculo));
	}
	
	@DeleteMapping("/veiculo/{id}")
	public ResponseEntity<Veiculo> deletarVeiculo(@PathVariable Long id){
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		veiculoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/veiculo/{id}")
	public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo){
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		veiculo = veiculoService.atualiza(id, veiculo);
		return ResponseEntity.ok(veiculo);
	}

}















