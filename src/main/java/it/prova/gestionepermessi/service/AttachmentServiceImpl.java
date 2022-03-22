package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository repository;

	@Override
	public List<Attachment> listAllElements() {
		return (List<Attachment>) repository.findAll();
	}

	@Override
	public Attachment caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}



	@Override
	public void aggiorna(Attachment filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void inserisciNuovo(Attachment filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void rimuovi(Attachment filmInstance) {
		repository.delete(filmInstance);
	}

	@Override
	public Page<Attachment> findByExampleWithPagination(Attachment example, Integer pageNo, Integer pageSize,
			String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
