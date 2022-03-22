package it.prova.gestionepermessi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = { "", "/richiestapermesso" })
public class RichiestaPermessoController {
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
}
