package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {
	public List<RichiestaPermesso> listAllElements();

	public RichiestaPermesso caricaSingoloElemento(Long id);

	public RichiestaPermesso caricaSingoloElementoConDipendenteEAttachment(Long id);

	public void aggiorna(RichiestaPermesso richiestaInstance);

	public void inserisciNuovo(RichiestaPermesso richiestaInstance);

	public void rimuovi(RichiestaPermesso richiestaInstance);

	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso example, Integer pageNo,
			Integer pageSize, String sortBy);

	public void changeRequestApprovement(Long idRichiesta);

	public void addRichiestaEInserisciMessaggio(RichiestaPermesso richiestaInstance);

	public void rimuoviRichiestaEMessaggioAssociatoEAttachment(Long idRichiesta);
}
