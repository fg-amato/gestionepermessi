package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;

	@Override
	public List<RichiestaPermesso> listAllElements() {
		return (List<RichiestaPermesso>) repository.findAll();
	}

	@Override
	public RichiestaPermesso caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(RichiestaPermesso filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void inserisciNuovo(RichiestaPermesso filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	public void rimuovi(RichiestaPermesso filmInstance) {
		repository.delete(filmInstance);
	}

	@Override
	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso example, Integer pageNo,
			Integer pageSize, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
