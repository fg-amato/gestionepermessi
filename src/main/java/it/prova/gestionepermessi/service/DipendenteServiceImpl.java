package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;

	@Autowired
	private UtenteRepository repositoryUtente;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${defaultPassword}")
	private String defaultPassword;

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllElements() {
		return (List<Dipendente>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		repository.delete(dipendenteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Dipendente> findByExampleWithPagination(Dipendente example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodFis()))
				predicates.add(cb.like(cb.upper(root.get("codFis")), "%" + example.getCodFis().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));

			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataassunzione"), example.getDataAssunzione()));

			if (example.getDataDimissioni() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("datadimissioni"), example.getDataAssunzione()));

			if (example.getSesso() != null)
				predicates.add(cb.equal(root.get("sesso"), example.getSesso()));

			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("datanascita"), example.getDataNascita()));

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
	public void inserisciDipendenteEdUtente(Dipendente dipendenteInstance) {

		Utente utenteInstance = dipendenteInstance.getUtente();
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(defaultPassword));
		repositoryUtente.save(utenteInstance);
		repository.save(dipendenteInstance);

	}

	@Transactional
	public void aggiornaDipendenteEUtente(Dipendente dipendenteInstance) {
		// deve aggiornare solo nome, cognome, username, ruoli
		Dipendente dipendenteReloaded = repository.findByIdWithUtente(dipendenteInstance.getId());
		if (dipendenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");

		dipendenteReloaded.setNome(dipendenteInstance.getNome());
		dipendenteReloaded.setCognome(dipendenteInstance.getCognome());
		dipendenteReloaded.setCodFis(dipendenteInstance.getCodFis());
		dipendenteReloaded.setDataNascita(dipendenteInstance.getDataNascita());
		dipendenteReloaded.setDataAssunzione(dipendenteInstance.getDataAssunzione());
		dipendenteReloaded.setDataDimissioni(dipendenteInstance.getDataDimissioni());
		dipendenteReloaded.setSesso(dipendenteInstance.getSesso());
		dipendenteReloaded.setEmail(dipendenteReloaded.buildEmail());

		Utente utenteAssociatoADipendente = dipendenteReloaded.getUtente();

		utenteAssociatoADipendente.setDipendente(dipendenteReloaded);
		
		//metodo che aggiorna i campi di nome, cognome ed username a partire dalle informazioni
		//del dipendente contenuto nell'oggetto passato al metodo statico
		Utente.updateUsernameUtenteAfterUpdatingDipendente(utenteAssociatoADipendente);
		
		// repositoryDipendente.save(dipendenteAssociatoAdUtente);
		repositoryUtente.save(utenteAssociatoADipendente);
	}

}
