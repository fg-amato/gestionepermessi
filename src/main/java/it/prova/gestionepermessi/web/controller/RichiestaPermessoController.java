package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = { "", "/richieste_permesso" })
public class RichiestaPermessoController {
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;

	@GetMapping
	public ModelAndView listAllRichieste() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermessoDTO> richieste = RichiestaPermessoDTO
				.createRichiesteDTOListFromModelList(richiestaPermessoService.listAllElements());
		mv.addObject("richieste_list_attribute", richieste);
		mv.setViewName("richieste_permesso/list");
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

		List<RichiestaPermessoDTO> richieste = RichiestaPermessoDTO.createRichiestaDTOListFromModelList(
				richiestaPermessoService.findByExampleWithPagination(richiestaPermessoExample.buildRichiestaModelForSearch(),
						pageNo, pageSize, sortBy).getContent());

		model.addAttribute("richiesta_permesso_list_attribute", richieste);
		return "richiesta_permesso/list";
	}
	
	@PostMapping("/cambiaApprovazione")
	public String cambiaApprovazione(@RequestParam(name = "idRichiestaForChangingApprovazione", required = true) Long idRichiesta) {
		richiestaPermessoService.changeRequestApprovement(idRichiesta);
		return "redirect:/richiesta_permesso";
	}
}
