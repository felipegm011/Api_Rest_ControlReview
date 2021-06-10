package br.com.api.controlreview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class HistoricoPeca {
	
	@Id
    @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@ManyToOne
	private OrdemServicoReview ordemservico;
	
	@Column(name = "comentario")
	private String comentario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public OrdemServicoReview getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServicoReview ordemservico) {
		this.ordemservico = ordemservico;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
