package br.com.api.controlreview.domain;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import br.com.api.controlreview.exception.NegocioException;

@Entity
public class ManutencaoPreventiva {
	
	/*
	 * depois refatorar o código para não permitir persistir com campos nulos
	 */
	
	@Id
    @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@ManyToOne
	private OrdemServicoReview ordemservico;
	
	@Column(name = "embreagem")
	private Boolean embreagem;

	
	@Column(name = "freio")
	private Boolean freio;
	
	
	@Column(name = "luzesPainel")
	private Boolean luzesPainel;
	
	
	@Column(name = "suspensao")
	private Boolean suspensao;
	
	
	@Column(name = "nivelOleo")
	private Boolean nivelOleo;
	
	
	@Column(name = "tracao")
	private Boolean tracao;
	
	
	@Column(name = "carburador")
	private Boolean carburador;
	
	
	@Column(name = "pneus")
	private Boolean pneu;
	

	@Column(name = "dataReview")
	private OffsetDateTime dataReview;
	
	@Column(name = "dataReviewFechamento")
	private OffsetDateTime dataReviewFechamento;
	
	@Column(name = "observacao")
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico statusManutencao;
	
	
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
	public Boolean getEmbreagem() {
		return embreagem;
	}
	public void setEmbreagem(Boolean embreagem) {
		this.embreagem = embreagem;
	}
	public Boolean getFreio() {
		return freio;
	}
	public void setFreio(Boolean freio) {
		this.freio = freio;
	}
	public Boolean getLuzesPainel() {
		return luzesPainel;
	}
	public void setLuzesPainel(Boolean luzesPainel) {
		this.luzesPainel = luzesPainel;
	}
	public Boolean getSuspensao() {
		return suspensao;
	}
	public void setSuspensao(Boolean suspensao) {
		this.suspensao = suspensao;
	}
	public Boolean getNivelOleo() {
		return nivelOleo;
	}
	public void setNivelOleo(Boolean nivelOleo) {
		this.nivelOleo = nivelOleo;
	}
	public Boolean getTracao() {
		return tracao;
	}
	public void setTracao(Boolean tracao) {
		this.tracao = tracao;
	}
	public Boolean getCarburador() {
		return carburador;
	}
	public void setCarburador(Boolean carburador) {
		this.carburador = carburador;
	}
	public Boolean getPneu() {
		return pneu;
	}
	public void setPneu(Boolean pneu) {
		this.pneu = pneu;
	}
	public OffsetDateTime getDataReview() {
		return dataReview;
	}
	public void setDataReview(OffsetDateTime dataReview) {
		this.dataReview = dataReview;
	}
	public OffsetDateTime getDataReviewFechamento() {
		return dataReviewFechamento;
	}
	public void setDataReviewFechamento(OffsetDateTime dataReviewFechamento) {
		this.dataReviewFechamento = dataReviewFechamento;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public StatusOrdemServico getStatusManutencao() {
		return statusManutencao;
	}
	public void setStatusManutencao(StatusOrdemServico statusManutencao) {
		this.statusManutencao = statusManutencao;
	}

	public void finalizar() {
		if(!StatusOrdemServico.ABERTA.equals(getStatusManutencao())) {
			throw new NegocioException("Os não pode está finalizafa");
		}
		setStatusManutencao(StatusOrdemServico.FINALIZADA);
		setDataReviewFechamento(OffsetDateTime.now());
	}
}
