package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {
	public List<RichiestaPermesso> listAllElements();

	public RichiestaPermesso caricaSingoloElemento(Long id);

	public void aggiorna(RichiestaPermesso filmInstance);

	public void inserisciNuovo(RichiestaPermesso filmInstance);

	public void rimuovi(RichiestaPermesso filmInstance);

	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso example, Integer pageNo,
			Integer pageSize, String sortBy);
}
