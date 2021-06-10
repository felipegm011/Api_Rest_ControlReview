package br.com.api.controlreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.controlreview.domain.HistoricoPeca;

public interface HistoricoPecaRepository extends JpaRepository<HistoricoPeca,Long> {

}
