package it.prova.gestionepermessi.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.gestionepermessi.model.Attachment;

public class AttachmentDTO {

	private Long id;

	private String nomeFile;

	private String contentType;

	private byte[] payload;

	public AttachmentDTO() {
		super();
	}

	public AttachmentDTO(String nomeFile, String contentType, byte[] payload) {
		super();
		this.nomeFile = nomeFile;
		this.contentType = contentType;
		this.payload = payload;
	}

	public AttachmentDTO(Long id, String nomeFile, String contentType, byte[] payload) {
		super();
		this.id = id;
		this.nomeFile = nomeFile;
		this.contentType = contentType;
		this.payload = payload;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public Attachment buildAttachmentModel() {
		return new Attachment(this.id, this.nomeFile, this.contentType, this.payload);
	}

	public static AttachmentDTO buildAttachmentDTOFromModel(Attachment richiestaModel) {
		return new AttachmentDTO(richiestaModel.getId(), richiestaModel.getNomeFile(), richiestaModel.getContentType(),
				richiestaModel.getPayload());
	}

	public static List<AttachmentDTO> createAttachmentDTOListFromModelList(List<Attachment> modelListInput) {
		return modelListInput.stream().map(richiestaEntity -> {
			return AttachmentDTO.buildAttachmentDTOFromModel(richiestaEntity);
		}).collect(Collectors.toList());
	}

}
