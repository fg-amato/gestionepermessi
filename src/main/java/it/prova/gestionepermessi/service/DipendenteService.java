package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {
	public List<Dipendente> listAllElements();

	public Dipendente caricaSingoloElemento(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);

	public Page<Dipendente> findByExampleWithPagination(Dipendente example, Integer pageNo, Integer pageSize,
			String sortBy);

	public void inserisciDipendenteEdUtente(Dipendente dipendenteInstance);

	public void aggiornaDipendenteEUtente(Dipendente dipendenteInstance);

	public List<Dipendente> cercaByCognomeENomeILike(String term);
}
