package it.prova.gestionepermessi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class Messaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "testo")
	private String testo;

	@Column(name = "oggetto")
	private String oggetto;

	@Column(name = "letto")
	private boolean letto = false;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "richiesta_id", referencedColumnName = "id", nullable = false)
	private RichiestaPermesso richiesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RichiestaPermesso getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(RichiestaPermesso richiesta) {
		this.richiesta = richiesta;
	}

	public Messaggio() {
		super();
	}

	public Messaggio(RichiestaPermesso rp) {
		super();
		this.testo = this.buildTestoMessaggio(rp);
		this.oggetto = this.buildOggettoMessaggio(rp);
		this.richiesta = rp;
	}

	public Messaggio(boolean letto, RichiestaPermesso rp) {
		super();
		this.testo = this.buildTestoMessaggio(rp);
		this.oggetto = this.buildOggettoMessaggio(rp);
		this.letto = letto;
		this.richiesta = rp;
	}

	public Messaggio(Long id, String testo, String oggetto, boolean letto) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
	}

	public Messaggio(RichiestaPermesso rp, Long id) {
		super();
		this.id = id;
		this.testo = this.buildTestoMessaggio(rp);
		this.oggetto = this.buildOggettoMessaggio(rp);
		this.richiesta = rp;
	}

	public Messaggio(boolean letto, RichiestaPermesso rp, Long id) {
		super();
		this.id = id;
		this.testo = this.buildTestoMessaggio(rp);
		this.oggetto = this.buildOggettoMessaggio(rp);
		this.letto = letto;
		this.richiesta = rp;
	}

	private String buildOggettoMessaggio(RichiestaPermesso rp) {
		String oggetto = "Richiesta permesso da parte di: " + rp.getDipendente().toString();
		return oggetto;
	}

	private String buildTestoMessaggio(RichiestaPermesso rp) {
		String testo = "Il dipendente " + rp.getDipendente().toString() + " ha richiesto un permesso per "
				+ rp.getTipoPermesso() + " dalla data: " + rp.getDataInizio() + " alla data: " + rp.getDataFine();
		if (rp.getNote().isBlank()) {
			testo += " senza alcuna nota,";
		} else {
			testo += " allegando la seguente nota \"" + rp.getNote() + "\"";
		}
		if (rp.getTipoPermesso() == TipoPermesso.MALATTIA) {
			testo += ", il seguente codice certificato: " + rp.getCodiceCertificato();
		}
		if (rp.getAttachment() != null) {
			testo += " ed l'allegato avente nome: " + rp.getAttachment().getNomeFile();
		}

		return testo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}

	public RichiestaPermesso getUtenteInserimento() {
		return richiesta;
	}

	public void setUtenteInserimento(RichiestaPermesso rp) {
		this.richiesta = rp;
	}

}