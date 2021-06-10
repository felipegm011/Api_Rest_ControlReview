package br.com.api.controlreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.controlreview.domain.ClienteReview;

public interface ClienteRepository extends JpaRepository<ClienteReview,Long> {

}
