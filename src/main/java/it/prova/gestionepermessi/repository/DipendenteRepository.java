package it.prova.gestionepermessi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteRepository
		extends PagingAndSortingRepository<Dipendente, Long>, JpaSpecificationExecutor<Dipendente> {

	@Query("from Dipendente d join fetch d.utente where d.id = ?1")
	public Dipendente findByIdWithUtente(Long id);
}
