package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {
	public List<Dipendente> listAllElements();

	public Dipendente caricaSingoloElemento(Long id);

	public void aggiorna(Dipendente filmInstance);

	public void inserisciNuovo(Dipendente filmInstance);

	public void rimuovi(Dipendente filmInstance);

	public Page<Dipendente> findByExampleWithPagination(Dipendente example, Integer pageNo, Integer pageSize,
			String sortBy);
}
