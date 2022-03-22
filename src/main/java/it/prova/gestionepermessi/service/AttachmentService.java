package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {
	public List<Attachment> listAllElements();

	public Attachment caricaSingoloElemento(Long id);


	public void aggiorna(Attachment filmInstance);

	public void inserisciNuovo(Attachment filmInstance);

	public void rimuovi(Attachment filmInstance);

	public Page<Attachment> findByExampleWithPagination(
			Attachment example, Integer pageNo, Integer pageSize,
			String sortBy);
}
