package br.com.api.controlreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.controlreview.domain.HistoricoPeca;
import br.com.api.controlreview.domain.OrdemServicoReview;
import br.com.api.controlreview.exception.NegocioException;
import br.com.api.controlreview.repository.HistoricoPecaRepository;
import br.com.api.controlreview.repository.OrdemServicoRepository;

@Service
public class HistoricoPecasService {

		
	@Autowired
	HistoricoPecaRepository historicopecarepository;
	
	@Autowired
	OrdemServicoRepository osRepository;
	
	public HistoricoPeca cadastro(HistoricoPeca pecas) {
		
		OrdemServicoReview os = osRepository.findById(pecas.getOrdemservico().getId())
				.orElseThrow(() -> new NegocioException("Código não existe"));
		
		// setando referencia da ordem de serviço antes de salvar o comentario, se não fizer isso o retorno do post a ordem de serviço vai ser null
		pecas.setOrdemservico(os);
		return historicopecarepository.save(pecas);
		
	}
	
	public void deletar(Long id) {
		historicopecarepository.deleteById(id);
	}
	
	public HistoricoPeca atualiza(Long id, HistoricoPeca pecas) {	
		//pegando a instancia do cliente selecionado para atualização do cadastro e atualizando
		pecas.setId(id);
		return pecas = historicopecarepository.save(pecas);
	}
}
