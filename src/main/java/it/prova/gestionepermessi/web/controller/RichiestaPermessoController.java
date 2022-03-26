package it.prova.gestionepermessi.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDipAttachmentDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.validation.ValidationForInsertUpdateRichiestaPermesso;

@Controller
@RequestMapping(value = "/richieste_permesso")
public class RichiestaPermessoController {
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;

	@Autowired
	private UtenteService utenteService;

//	@Autowired
//	private AttachmentService attachmentService;

	// @Autowired
	// private DipendenteService dipendenteService;

	@GetMapping
	public ModelAndView listAllRichieste() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermessoDTO> richieste = RichiestaPermessoDTO
				.createRichiesteDTOListFromModelList(richiestaPermessoService.listAllElements());
		mv.addObject("richieste_list_attribute", richieste);
		mv.setViewName("richiesta_permesso/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchRichiesta(Model model) {
		return "richiesta_permesso/search";
	}

	@PostMapping("/list")
	public String listRichieste(RichiestaPermessoDTO richiestaPermessoExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "9") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {
		List<RichiestaPermessoDTO> richieste = RichiestaPermessoDTO
				.createRichiestaDTOListFromModelList(richiestaPermessoService
						.findByExampleWithPagination(richiestaPermessoExample.buildRichiestaModelForSearch(), pageNo,
								pageSize, sortBy)
						.getContent());
		model.addAttribute("richieste_list_attribute", richieste);
		return "richiesta_permesso/list";
	}

	@PostMapping("/cambiaApprovazione")
	public String cambiaApprovazione(
			@RequestParam(name = "idRichiestaForChangingApprovazione", required = true) Long idRichiestaForChangingApprovazione) {
		richiestaPermessoService.changeRequestApprovement(idRichiestaForChangingApprovazione);
		return "redirect:/richieste_permesso";
	}

	@GetMapping("/search_personal")
	public String searchRichiestaUserInSession(Model model) {
		return "richiesta_permesso/search_personal";
	}

	@PostMapping("/listPersonal")
	public String listRichiestePersonali(RichiestaPermessoDTO richiestaPermessoExample,
			@RequestParam(name = "usernameUtente", required = true) String usernameUtente,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "9") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {
		Utente utenteInSession = utenteService.trovaByUsernameWithDipendente(usernameUtente);
		richiestaPermessoExample
				.setDipendente(DipendenteDTO.buildDipendenteDTOFromModel(utenteInSession.getDipendente()));
		List<RichiestaPermessoDTO> richieste = RichiestaPermessoDTO
				.createRichiestaDTOListFromModelList(richiestaPermessoService
						.findByExampleWithPagination(richiestaPermessoExample.buildRichiestaModelForSearch(), pageNo,
								pageSize, sortBy)
						.getContent());
		model.addAttribute("richieste_list_attribute", richieste);
		return "richiesta_permesso/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoDTO());
		return "richiesta_permesso/insert";
	}

	@PostMapping("/save")
	public String save(@RequestParam("file") MultipartFile file, Attachment attachment, @Validated({
			ValidationForInsertUpdateRichiestaPermesso.class }) @ModelAttribute("insert_richiesta_attr") RichiestaPermessoDTO rpDTO,
			BindingResult result, @RequestParam(name = "usernameUtente", required = true) String usernameUtente,
			@RequestParam(name = "check", required = false) String checked, Model model,
			RedirectAttributes redirectAttrs) {

		if (checked == null && rpDTO.getDataFine() == null) {
			result.rejectValue("dataFine", "dataFine.notnull");
		}

		Utente utenteInSession = utenteService.trovaByUsernameWithDipendente(usernameUtente);
		rpDTO.setDipendente(DipendenteDTO.buildDipendenteDTOFromModel(utenteInSession.getDipendente()));

		if (!result.hasFieldErrors("tipoPermesso") && rpDTO.getTipoPermesso() != TipoPermesso.FERIE
				&& rpDTO.getCodiceCertificato().isBlank()) {
			result.rejectValue("tipoPermesso", "codObbligatorio");
		}

		if (result.hasErrors()) {
			return "richiesta_permesso/insert";
		}

		RichiestaPermesso toAdd = rpDTO.buildRichiestaModelForInsert();

		try {
			attachment.setContentType(file.getContentType());
			attachment.setNomeFile(file.getOriginalFilename());
			attachment.setPayload(file.getBytes());
			toAdd.setAttachment(attachment);
		} catch (IOException e) {
			throw new RuntimeException("Problema nell'upload file", e);
		}
		richiestaPermessoService.addRichiestaEInserisciMessaggio(toAdd);
		// attachmentService.inserisciNuovo(attachment);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richieste_permesso";
	}

	@GetMapping("/show/{idRichiesta}")
	public String show(@PathVariable(required = true) Long idRichiesta, Model model) {
		RichiestaPermesso richiestaModel = richiestaPermessoService
				.caricaSingoloElementoConDipendenteEAttachment(idRichiesta);
		model.addAttribute("show_richiesta_attr",
				RichiestaPermessoDipAttachmentDTO.buildRichiestaPermessoDipAttachmentFromModel(richiestaModel));
		return "richiesta_permesso/show";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam(name = "idRichiestaToDelete", required = true) Long idRichiesta,
			RedirectAttributes redirectAttrs) {
		richiestaPermessoService.rimuoviRichiestaEMessaggioAssociatoEAttachment(idRichiesta);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richieste_permesso";
	}

	@GetMapping("/edit/{idRichiesta}")
	public String edit(@PathVariable(required = true) Long idRichiesta, Model model) {
		RichiestaPermesso richiestaModel = richiestaPermessoService.caricaSingoloElemento(idRichiesta);
		model.addAttribute("edit_richiesta_attr",
				RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaModel));
		return "richiesta_permesso/edit";
	}

	@PostMapping("/update")
	public String update(@Validated({
			ValidationForInsertUpdateRichiestaPermesso.class }) @ModelAttribute("edit_richiesta_attr") RichiestaPermessoDTO rpDTO,
			BindingResult result, @RequestParam(name = "check", required = false) String checked, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (checked == null && rpDTO.getDataFine() == null) {
			result.rejectValue("dataFine", "dataFine.notnull");
		}

		if (!result.hasFieldErrors("tipoPermesso") && rpDTO.getTipoPermesso() != TipoPermesso.FERIE
				&& rpDTO.getCodiceCertificato().isBlank()) {
			result.rejectValue("tipoPermesso", "codObbligatorio");
		}

		if (result.hasErrors()) {
			return "richiesta_permesso/edit";
		}
		rpDTO.setDataFine(null);
		richiestaPermessoService.aggiornaRichiestaEMessaggio(rpDTO.buildRichiestaModelForUpdate());
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richieste_permesso";
	}

}
