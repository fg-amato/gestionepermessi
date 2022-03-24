package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public Utente caricaSingoloUtenteConRuoli(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public Page<Utente> findByExampleWithPagination(Utente example, Integer pageNo, Integer pageSize, String sortBy);

	public Utente findByUsernameAndPassword(String username, String password);

	public Utente eseguiAccesso(String username, String password);

	public void changeUserAbilitation(Long utenteInstanceId);

	public Utente findByUsername(String username);

	public void changePassword(Long idUtente, String nuovaPassword, String vecchiaPassword);

	public void resetPassword(Long idUtente);

	public void inserisciUtenteEDipendente(Utente utenteInstance);

	public void aggiornaUtenteEDipendente(Utente utenteInstance);

	public Utente trovaByUsernameWithDipendente(String usernameUtente);

}
