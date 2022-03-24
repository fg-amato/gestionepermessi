package it.prova.gestionepermessi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "richiestapermesso")
public class RichiestaPermesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codicecertificato")
	private String codiceCertificato;
	@Column(name = "datainizio")
	private Date dataInizio;
	@Column(name = "datafine")
	private Date dataFine;
	@Enumerated(EnumType.STRING)
	private TipoPermesso tipoPermesso;
	@Column(name = "approvato")
	private Boolean approvato;
	@Column(name = "note")
	private String note;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment_id", referencedColumnName = "id", nullable = true)
	private Attachment attachment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dipendente_id", nullable = false)
	private Dipendente dipendente;

	public RichiestaPermesso() {
		super();
	}

	public RichiestaPermesso(Long id) {
		super();
		this.id = id;
	}

	public RichiestaPermesso(String codiceCertificato, Date dataInizio, Date dataFine, TipoPermesso tipoPermesso,
			String note, Dipendente dipendente) {
		super();
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;
		this.dipendente = dipendente;
	}

	public RichiestaPermesso(Long id, String codiceCertificato, Date dataInizio, Date dataFine,
			TipoPermesso tipoPermesso, String note) {
		super();
		this.id = id;
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;

	}

	public RichiestaPermesso(Long id, String codiceCertificato, Date dataInizio, Date dataFine,
			TipoPermesso tipoPermesso, String note, Dipendente dipendente) {
		super();
		this.id = id;
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;
		this.dipendente = dipendente;

	}

	public RichiestaPermesso(Long id, String codiceCertificato, Date dataInizio, Date dataFine,
			TipoPermesso tipoPermesso, String note, Attachment attachment, Dipendente dipendente) {
		super();
		this.id = id;
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;
		this.attachment = attachment;
		this.dipendente = dipendente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(Boolean approvato) {
		this.approvato = approvato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

}
