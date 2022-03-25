package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
		Specification<Messaggio> specificationCriteria = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (example.isLetto() != null) {
				predicates.add(cb.equal(root.get("letto"), example.isLetto()));
			}

			if (StringUtils.isNotEmpty(example.getOggetto()))
				predicates.add(cb.like(cb.upper(root.get("oggetto")), "%" + example.getOggetto().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getTesto()))
				predicates.add(cb.like(cb.upper(root.get("testo")), "%" + example.getTesto().toUpperCase() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public Messaggio caricaSingoloElementoConRichiesta(Long idMessaggio) {
		return repository.caricaSingoloElementoEager(idMessaggio);
	}

	@Override
	public boolean listNonLetti() {
		return (repository.findAllByLetto(false).size()) > 0;
	}

	@Override
	public void leggi(Long idMessaggio) {
		Messaggio messaggioReloaded = repository.findById(idMessaggio).orElse(null);
		if (messaggioReloaded == null) {
			throw new RuntimeException("Errore");
		}

		messaggioReloaded.setLetto(true);

		repository.save(messaggioReloaded);
	}

}
