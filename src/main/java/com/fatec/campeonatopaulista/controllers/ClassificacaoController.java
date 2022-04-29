package com.fatec.campeonatopaulista.controllers;

import com.fatec.campeonatopaulista.services.GrupoService;
import com.fatec.campeonatopaulista.services.GrupoServiceFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/classificacao")
@Controller
public class ClassificacaoController {
	private GrupoService grupoService;

	public ClassificacaoController() {
		super();

		try {
			this.grupoService = GrupoServiceFactory.create();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@GetMapping("/geral")
	protected String listarResultados(Model model) {
		var times = this.grupoService.listarGrupos().get(0).getTimes();
		model.addAttribute("times", times);
		return "classificacao-geral";
	}

}
