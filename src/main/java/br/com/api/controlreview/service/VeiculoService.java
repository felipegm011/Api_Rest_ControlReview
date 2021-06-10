package br.com.api.controlreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.controlreview.domain.Veiculo;
import br.com.api.controlreview.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	public Veiculo cadastrar(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}
	
	public void deletar(Long id){
		veiculoRepository.deleteById(id);
	}
	
	public Veiculo atualiza(Long id, Veiculo veiculo) {
		veiculo.setId(id);
		return veiculo = veiculoRepository.save(veiculo);
	}

}
