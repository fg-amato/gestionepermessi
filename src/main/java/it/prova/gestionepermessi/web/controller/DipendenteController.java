package it.prova.gestionepermessi.web.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;
import it.prova.gestionepermessi.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/dipendente")
public class DipendenteController {

	@Autowired
	private RuoloService ruoloService;

	@Autowired
	private DipendenteService dipendenteService;

	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<DipendenteDTO> dipendenti = DipendenteDTO
				.createDipendenteDTOListFromModelList(dipendenteService.listAllElements());
		mv.addObject("dipendente_list_attribute", dipendenti);
		mv.setViewName("dipendente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchDipendente(Model model) {
		return "dipendente/search";
	}

	@PostMapping("/list")
	public String listDipendenti(DipendenteDTO dipendenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "9") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		List<DipendenteDTO> dipendenti = DipendenteDTO.createDipendenteDTOListFromModelList(
				dipendenteService.findByExampleWithPagination(dipendenteExample.buildDipendenteModelForSearch(), pageNo,
						pageSize, sortBy).getContent());

		model.addAttribute("dipendente_list_attribute", dipendenti);
		return "dipendente/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("ruoli_totali_attr",
				RuoloDTO.createRuoloDTOListFromModelList(ruoloService.findAllExceptAdmin()));
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "dipendente/insert";
	}

	@PostMapping("/save")
	public String save(
			@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr",
					RuoloDTO.createRuoloDTOListFromModelList(ruoloService.findAllExceptAdmin()));
			return "dipendente/insert";
		}

		dipendenteService.inserisciDipendenteEdUtente(dipendenteDTO.buildDipendenteModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/dipendente";
	}

	@GetMapping("/show/{idDipendente}")
	public String show(@PathVariable(required = true) Long idDipendente, Model model) {
		Dipendente dipendenteModel = dipendenteService.caricaSingoloElemento(idDipendente);
		model.addAttribute("show_dipendente_attr", DipendenteDTO.buildDipendenteDTOFromModel(dipendenteModel));
		return "dipendente/show";
	}
}
