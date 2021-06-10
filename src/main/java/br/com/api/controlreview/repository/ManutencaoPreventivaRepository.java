package br.com.api.controlreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.controlreview.domain.ManutencaoPreventiva;

public interface ManutencaoPreventivaRepository extends JpaRepository<ManutencaoPreventiva,Long>{

}
