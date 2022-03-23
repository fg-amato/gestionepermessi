package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.repository.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;

	@Transactional(readOnly = true)
	public List<Ruolo> listAll() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Transactional
	public void rimuovi(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = true)
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

	@Transactional(readOnly = true)
	public List<Ruolo> findAllExceptAdmin() {
		List<Ruolo> result = new ArrayList<>();
		Ruolo temp = ruoloRepository.findByDescrizioneAndCodice("Dipendente User", "ROLE_DIPENDENTE_USER");
		System.out.println(temp.getCodice());
		result.add(temp);
		result.add(ruoloRepository.findByDescrizioneAndCodice("BackOffice User", "ROLE_BO_USER"));

		return result;
	}

}
