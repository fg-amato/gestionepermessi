package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.repository.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {

	@Autowired
	private MessaggioRepository repository;

	@Override
	public List<Messaggio> listAllElements() {
		return (List<Messaggio>) repository.findAll();
	}

	@Override
	public Messaggio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Messaggio filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void inserisciNuovo(Messaggio filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void rimuovi(Messaggio filmInstance) {
		repository.delete(filmInstance);

	}

	@Override
	public Page<Messaggio> findByExampleWithPagination(Messaggio example, Integer pageNo, Integer pageSize,
			String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
