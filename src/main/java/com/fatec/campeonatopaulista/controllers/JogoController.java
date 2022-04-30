package com.fatec.campeonatopaulista.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.campeonatopaulista.models.Jogo;
import com.fatec.campeonatopaulista.persistence.JogoDAO;
import com.fatec.campeonatopaulista.services.JogoService;

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

	private JogoService jogoService;

	private static final String GRUPO_DE_RECURSOS = "jogos";

	@Autowired
	public JogoController(JogoDAO jogoDAO, JogoService jogoService) {
		this.jogoDAO = jogoDAO;
		this.jogoService = jogoService;
	}

	@GetMapping("/resultados")
	protected ModelAndView resultados(@RequestParam(required = false, defaultValue = "") String dataDoJogo) {
		var existeDataDoJogo = dataDoJogo.isBlank();
		var destino = existeDataDoJogo ? "redirect:/jogos" : "resultados-dos-jogos";

		var viewModel = new ModelAndView(destino);
		viewModel.addObject(GRUPO_DE_RECURSOS, jogoDAO.encontrarTodosPelaData(dataDoJogo));

		return viewModel;
	}

	@GetMapping("/quartas-de-final")
	protected ModelAndView quartasDeFinal() {
		var viewModel = new ModelAndView("quartas-de-final");
		viewModel.addObject(GRUPO_DE_RECURSOS, jogoDAO.listarQuartasDeFinal());
		return viewModel;
	}

	@PostMapping(value = "/resultados")
	public ModelAndView registrarResultados(
			@RequestParam("dataDoJogo") String dataDoJogo,
			@RequestParam("codigo[]") List<Integer> codigos,
			@RequestParam("golsTimeA[]") List<Integer> golsDosTimesA,
			@RequestParam("golsTimeB[]") List<Integer> golsDosTimesB) {
		jogoService.registrarResultados(
				codigos, golsDosTimesA, golsDosTimesB);
		return new ModelAndView("redirect:/jogos/resultados?dataDoJogo=" + dataDoJogo);
	}

	@GetMapping("")
	protected String procurar(@RequestParam(required = false) String dataDoJogo, Model model) {
		List<Jogo> jogos = new ArrayList<>();
		var existeDataDoJogo = dataDoJogo != null;

		if (existeDataDoJogo) {
			jogos = this.jogoDAO.encontrarTodosPelaData(dataDoJogo);
		}

		model.addAttribute(GRUPO_DE_RECURSOS, jogos);
		model.addAttribute("dataDoJogo", existeDataDoJogo ? dataDoJogo : "");
		return GRUPO_DE_RECURSOS;
	}

	@PostMapping("")
	protected String embaralhar() {
		try {
			this.jogoDAO.embaralhar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/jogos";
	}
}
