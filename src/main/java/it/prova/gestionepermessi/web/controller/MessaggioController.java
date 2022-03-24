package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.MessaggioDTO;
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
}
