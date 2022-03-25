package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.MessaggioDTO;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.service.MessaggioService;

@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {

	@Autowired
	private MessaggioService messaggioService;

	@GetMapping
	public ModelAndView listAllMessaggi() {
		ModelAndView mv = new ModelAndView();
		List<MessaggioDTO> messaggi = MessaggioDTO
				.createMessaggioDTOListFromModelList(messaggioService.listAllElements());
		mv.addObject("messaggi_list_attribute", messaggi);
		mv.setViewName("messaggi_permesso/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchMessaggio(Model model) {
		return "messaggio/search";
	}

	@PostMapping("/list")
	public String listMessaggi(MessaggioDTO messaggioExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "9") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<MessaggioDTO> messaggi = MessaggioDTO.createMessaggioDTOListFromModelList(messaggioService
				.findByExampleWithPagination(messaggioExample.buildMessaggioModelForSearch(), pageNo, pageSize, sortBy)
				.getContent());
		model.addAttribute("messaggi_list_attribute", messaggi);
		return "messaggio/list";
	}

	@GetMapping("/show/{idMessaggio}")
	public String show(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messaggioModel = messaggioService.caricaSingoloElementoConRichiesta(idMessaggio);
		model.addAttribute("show_messaggio_attr", MessaggioDTO.buildMessaggioDTOFromModel(messaggioModel));
		return "richiesta_permesso/show";
	}

	@GetMapping("/listMessaggi")
	public String listMessaggi(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "9") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<MessaggioDTO> messaggi = MessaggioDTO.createMessaggioDTOListFromModelList(messaggioService
				.findByExampleWithPagination(new Messaggio(false), pageNo, pageSize, sortBy).getContent());
		model.addAttribute("messaggi_list_attribute", messaggi);
		return "messaggio/list";
	}
}
