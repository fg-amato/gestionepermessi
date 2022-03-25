package it.prova.gestionepermessi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoRepository
		extends PagingAndSortingRepository<RichiestaPermesso, Long>, JpaSpecificationExecutor<RichiestaPermesso> {

	@Query("from RichiestaPermesso rp join fetch rp.dipendente left join fetch rp.attachment where rp.id = ?1")
	public RichiestaPermesso findRequestWithDipendenteAndAllegato(Long id);
}
