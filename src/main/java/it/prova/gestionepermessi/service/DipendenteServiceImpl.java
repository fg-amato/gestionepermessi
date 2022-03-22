package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;

	@Override
	public List<Dipendente> listAllElements() {
		return (List<Dipendente>) repository.findAll();
	}

	@Override
	public Dipendente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Dipendente filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void inserisciNuovo(Dipendente filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void rimuovi(Dipendente filmInstance) {
		repository.delete(filmInstance);
	}

	@Override
	public Page<Dipendente> findByExampleWithPagination(Dipendente example, Integer pageNo, Integer pageSize,
			String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
