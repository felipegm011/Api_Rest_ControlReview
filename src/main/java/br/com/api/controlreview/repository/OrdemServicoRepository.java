package br.com.api.controlreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.controlreview.domain.OrdemServicoReview;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoReview,Long>{

}
