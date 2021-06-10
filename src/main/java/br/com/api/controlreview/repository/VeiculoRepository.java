package br.com.api.controlreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.controlreview.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

}
