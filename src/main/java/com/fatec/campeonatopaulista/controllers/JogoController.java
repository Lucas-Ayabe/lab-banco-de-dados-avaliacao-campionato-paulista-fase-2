package com.fatec.campeonatopaulista.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.campeonatopaulista.models.Jogo;
import com.fatec.campeonatopaulista.persistence.JogoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/jogos")
@Controller
public class JogoController {
	@Qualifier("sqlServerJogoDAO")
	private JogoDAO jogoDAO;

	private static final String GRUPO_DE_RECURSOS = "jogos";

	@Autowired
	public JogoController(JogoDAO jogoDAO) {
		this.jogoDAO = jogoDAO;
	}

	@GetMapping("/resultados")
	protected ModelAndView resultados(@RequestParam(required = false, defaultValue = "") String dataDoJogo) {
		var existeDataDoJogo = dataDoJogo.isBlank();
		var destino = existeDataDoJogo ? "redirect:/jogos" : "resultados-dos-jogos";

		var viewModel = new ModelAndView(destino);
		viewModel.addObject(GRUPO_DE_RECURSOS, jogoDAO.findAllByDate(dataDoJogo));

		return viewModel;
	}

	@GetMapping("")
	protected String procurar(@RequestParam(required = false) String dataDoJogo, Model model) {
		List<Jogo> jogos = new ArrayList<>();
		var existeDataDoJogo = dataDoJogo != null;

		if (existeDataDoJogo) {
			jogos = this.jogoDAO.findAllByDate(dataDoJogo);
		}

		model.addAttribute(GRUPO_DE_RECURSOS, jogos);
		model.addAttribute("dataDoJogo", existeDataDoJogo ? dataDoJogo : "");
		return GRUPO_DE_RECURSOS;
	}

	@PostMapping("")
	protected String sortear() {
		try {
			this.jogoDAO.sort();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/jogos";
	}
}
