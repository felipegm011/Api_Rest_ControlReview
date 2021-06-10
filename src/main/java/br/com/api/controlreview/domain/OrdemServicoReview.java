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
public class OrdemServicoReview {
	
	@Id
    @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@ManyToOne
	private ClienteReview cliente;
	
	@Column(name = "tipoOs")
	private String tipoOrdemservico;
	
	@ManyToOne
	private Veiculo veiculo;
	
	@Column(name = "km")
	private Long km;
	
	@Column(name = "dataAbertura")
	private OffsetDateTime dataAbertura;
	
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico statusOs;
	
	@Column(name = "dataFechamento")
	private OffsetDateTime dataFechamento;
	
	@Column(name = "observacao")
	private String observacao;
	
	
	
	public Long getKm() {
		return km;
	}

	public void setKm(Long km) {
		this.km = km;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteReview getCliente() {
		return cliente;
	}

	public void setCliente(ClienteReview cliente) {
		this.cliente = cliente;
	}

	public String getTipoOrdemservico() {
		return tipoOrdemservico;
	}

	public void setTipoOrdemservico(String tipoOrdemservico) {
		this.tipoOrdemservico = tipoOrdemservico;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public StatusOrdemServico getStatusOs() {
		return statusOs;
	}

	public void setStatusOs(StatusOrdemServico statusOs) {
		this.statusOs = statusOs;
	}

	public OffsetDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(OffsetDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void finalizar() {
		if(!StatusOrdemServico.ABERTA.equals(getStatusOs())) {
			throw new NegocioException("Os não pode está finalizafa");
		}
		setStatusOs(StatusOrdemServico.FINALIZADA);
		setDataFechamento(OffsetDateTime.now());
	}
	
	public void cancelar() {
		if(!StatusOrdemServico.ABERTA.equals(getStatusOs())) {
			throw new NegocioException("Os não pode está finalizafa");
		}
		setStatusOs(StatusOrdemServico.CANCELADA);
		setDataFechamento(OffsetDateTime.now());
	}

}
