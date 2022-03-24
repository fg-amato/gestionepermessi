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
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.MessaggioRepository;
import it.prova.gestionepermessi.repository.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;

	@Autowired
	private MessaggioRepository repositoryMessage;

	@Override
	@Transactional(readOnly = true)
	public List<RichiestaPermesso> listAllElements() {
		return (List<RichiestaPermesso>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RichiestaPermesso caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(RichiestaPermesso filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(RichiestaPermesso filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	@Transactional
	public void rimuovi(RichiestaPermesso filmInstance) {
		repository.delete(filmInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso example, Integer pageNo,
			Integer pageSize, String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(root.get("tipoPermesso"), example.getTipoPermesso()));

			if (example.getDipendente() != null && example.getDipendente().getId() != null)
				predicates.add(cb.equal(cb.upper(root.get("dipendente")), example.getDipendente().getId()));

			if (example.isApprovato() != null) {
				System.out.println("APPROVE" + example.isApprovato());
				predicates.add(cb.equal(root.get("approvato"), example.isApprovato()));
			}

			if (StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("codiceCertificato")),
						"%" + example.getCodiceCertificato().toUpperCase() + "%"));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));

			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));

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
	@Transactional
	public void changeRequestApprovement(Long idRichiesta) {
		RichiestaPermesso richiestaInstance = caricaSingoloElemento(idRichiesta);
		if (richiestaInstance == null)
			throw new RuntimeException("Elemento non trovato.");

		richiestaInstance.setApprovato(!richiestaInstance.isApprovato());
	}

	@Override
	@Transactional
	public void addRichiestaEInserisciMessaggio(RichiestaPermesso richiestaInstance) {
		Messaggio messaggioInstance = RichiestaPermesso.createMessageFromRichiesta(richiestaInstance);
		richiestaInstance.setApprovato(false);
		repository.save(richiestaInstance);
		repositoryMessage.save(messaggioInstance);

	}

}
