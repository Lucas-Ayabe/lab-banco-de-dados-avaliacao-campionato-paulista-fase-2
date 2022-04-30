package com.fatec.campeonatopaulista.controllers;

import com.fatec.campeonatopaulista.services.GrupoService;
import com.fatec.campeonatopaulista.services.ResultadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/classificacao")
@Controller
public class ClassificacaoController {
	private ResultadoService resultadoService;

	@Autowired
	public ClassificacaoController(ResultadoService resultadoService) {
		this.resultadoService = resultadoService;
	}

	@GetMapping("/geral")
	protected String listarResultados(Model model) {
		model.addAttribute("times", resultadoService.listarResultadosGerais());
		return "classificacao-geral";
	}

}
