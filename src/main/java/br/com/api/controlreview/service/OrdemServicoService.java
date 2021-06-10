package br.com.api.controlreview.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.controlreview.domain.ClienteReview;
import br.com.api.controlreview.domain.OrdemServicoReview;
import br.com.api.controlreview.domain.StatusOrdemServico;
import br.com.api.controlreview.domain.Veiculo;
import br.com.api.controlreview.exception.NegocioException;
import br.com.api.controlreview.repository.ClienteRepository;
import br.com.api.controlreview.repository.OrdemServicoRepository;
import br.com.api.controlreview.repository.VeiculoRepository;


@Service
public class OrdemServicoService {
	
	@Autowired
	OrdemServicoRepository osRepository;
	
	@Autowired
	ClienteRepository  clienteRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;

	public OrdemServicoReview cadastrar(OrdemServicoReview os) {
		
		// verificando se id do cliente passado existe
		ClienteReview cliente = clienteRepository.findById(os.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Código não existe"));
		
		// verificando se id do veiculo passado existe
		Veiculo veiculo = veiculoRepository.findById(os.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Código não existe"));
		
		//setando na os referencia do cliente e do veiculo passado na OS
		os.setCliente(cliente);
		os.setVeiculo(veiculo);
		
		//setando status inicial da os
		os.setStatusOs(StatusOrdemServico.ABERTA);
		
		//setando data do sistema na os automaticamente
		os.setDataAbertura(OffsetDateTime.now());
		
		return osRepository.save(os);
	}
	
	public void finalizar(Long idOs) {
		
		//verificando se id passado existe
		OrdemServicoReview os = osRepository.findById(idOs)
				.orElseThrow(() -> new NegocioException("Os não encontrada"));
		
		//escopo do metodo está na classe OrdemServico
		os.finalizar();
		osRepository.save(os);
	}
	
	public OrdemServicoReview atualiza(Long id, OrdemServicoReview os) {
		
		// verificando se id do cliente passado existe
			ClienteReview cliente = clienteRepository.findById(os.getCliente().getId())
					.orElseThrow(() -> new NegocioException("Código não existe"));
	
		// verificando se id do veiculo passado existe
			Veiculo veiculo = veiculoRepository.findById(os.getCliente().getId())
					.orElseThrow(() -> new NegocioException("Código não existe"));
		os.setId(id);	
		os.setCliente(cliente);
		os.setVeiculo(veiculo);
		os.setStatusOs(StatusOrdemServico.ABERTA);
		os.setDataAbertura(OffsetDateTime.now());
		
		return os = osRepository.save(os);
	}
	
	public void deletar(Long id){
		osRepository.deleteById(id);
	}
	
	public void cancelar(Long idOs) {
		
		OrdemServicoReview os = osRepository.findById(idOs)
				.orElseThrow(() -> new NegocioException("Os não encontrada"));

		os.cancelar();
		osRepository.save(os);
	}

		
}
