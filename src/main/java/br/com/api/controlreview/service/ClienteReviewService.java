package br.com.api.controlreview.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.controlreview.domain.ClienteReview;
import br.com.api.controlreview.repository.ClienteRepository;

@Service
public class ClienteReviewService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteReview cadastro(ClienteReview cliente) {
		//implmentar regras de negocio
		return clienteRepository.save(cliente);
	}
	
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public ClienteReview atualiza(Long id, ClienteReview cliente) {
		
		//pegando a instancia do cliente selecionado para atualização do cadastro e atualizando
		cliente.setId(id);
		return cliente = clienteRepository.save(cliente);
	}
}
