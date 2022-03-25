package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {
	public List<Messaggio> listAllElements();

	public Messaggio caricaSingoloElemento(Long id);

	public void aggiorna(Messaggio filmInstance);

	public void inserisciNuovo(Messaggio filmInstance);

	public void rimuovi(Messaggio filmInstance);

	public Page<Messaggio> findByExampleWithPagination(Messaggio example, Integer pageNo, Integer pageSize,
			String sortBy);

	public Messaggio caricaSingoloElementoConRichiesta(Long idMessaggio);

	public boolean listNonLetti();
}
