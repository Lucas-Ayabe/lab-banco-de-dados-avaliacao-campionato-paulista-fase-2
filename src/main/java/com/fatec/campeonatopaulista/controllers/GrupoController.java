package com.fatec.campeonatopaulista.controllers;

import com.fatec.campeonatopaulista.services.GrupoService;
import com.fatec.campeonatopaulista.services.ResultadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/grupos")
@Controller
public class GrupoController {
	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ResultadoService resultadoService;
	private static final String GRUPO_DE_RECURSOS = "grupos";

	@GetMapping("")
	protected String listar(Model model) {
		var grupos = grupoService.listarGrupos();
		var rebaixados = resultadoService.listarTimesRebaixados();
		model.addAttribute(GRUPO_DE_RECURSOS, grupos);
		model.addAttribute("rebaixados", rebaixados);
		return GRUPO_DE_RECURSOS;
	}

	@GetMapping("/resultados")
	protected String listarResultados(Model model) {
		var resultados = resultadoService.listarResultadosDosGrupos();
		model.addAttribute("resultadosDosGrupos", resultados);
		model.addAttribute("rebaixados", resultadoService.listarTimesRebaixados());
		return "resultados-dos-grupos";
	}

	@PostMapping("")
	protected String sortear(Model model) {
		this.grupoService.sortearGrupos();
		return "/grupos";
	}

}
