package it.prova.gestionepermessi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository
		extends PagingAndSortingRepository<Messaggio, Long>, JpaSpecificationExecutor<Messaggio> {

	@Query("from Messaggio m join fetch m.richiesta rp where rp.id = ?1")
	Messaggio findRequestWithMessage(Long idRichiesta);

	List<Messaggio> findAllByLetto(boolean b);
	
	@Query("from Messaggio m join fetch m.richiesta where m.id = ?1")
	Messaggio caricaSingoloElementoEager(Long idMessaggio);

}
