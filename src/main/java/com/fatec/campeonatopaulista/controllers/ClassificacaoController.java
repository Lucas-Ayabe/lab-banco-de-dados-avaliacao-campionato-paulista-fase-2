package com.fatec.campeonatopaulista.controllers;

import com.fatec.campeonatopaulista.services.GrupoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/classificacao")
@Controller
public class ClassificacaoController {
	@Qualifier("sqlServerGrupoService")
	private GrupoService grupoService;

	@Autowired
	public ClassificacaoController(GrupoService grupoService) {
		this.grupoService = grupoService;
	}

	@GetMapping("/geral")
	protected String listarResultados(Model model) {
		var times = this.grupoService.listarGrupos().get(0).getTimes();
		model.addAttribute("times", times);
		return "classificacao-geral";
	}

}
