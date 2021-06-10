package br.com.api.controlreview.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.controlreview.domain.ManutencaoPreventiva;
import br.com.api.controlreview.domain.OrdemServicoReview;
import br.com.api.controlreview.domain.StatusOrdemServico;
import br.com.api.controlreview.exception.NegocioException;
import br.com.api.controlreview.repository.ManutencaoPreventivaRepository;
import br.com.api.controlreview.repository.OrdemServicoRepository;

@Service
public class ManutencaoPreventivaService {
	
	@Autowired
	ManutencaoPreventivaRepository preventivaRepository;
	
	@Autowired
	OrdemServicoRepository osRepository;
	
	public ManutencaoPreventiva cadastrar(ManutencaoPreventiva preventiva) {
		
		OrdemServicoReview os = osRepository.findById(preventiva.getOrdemservico().getId())
				.orElseThrow(() -> new NegocioException("Código não existe"));
		
		preventiva.setOrdemservico(os);
		preventiva.setStatusManutencao(StatusOrdemServico.ABERTA);
		preventiva.setDataReview(OffsetDateTime.now());
		return preventivaRepository.save(preventiva);
		
	}
	
	public void finalizar(Long idOs) {
		
		ManutencaoPreventiva manutencao = preventivaRepository.findById(idOs)
				.orElseThrow(() -> new NegocioException("Os não encontrada"));
		
		manutencao.finalizar();
		preventivaRepository.save(manutencao);
	}

}
