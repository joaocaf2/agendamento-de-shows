package com.agendamento.shows.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;

@Controller
public class ShowController {

	@Autowired
	private ShowRepository showRepository;

	@RequestMapping("/show/formulario/cadastrar")
	public String formulario(Showw show) {
		return "show/formulario";
	}

	@PostMapping("/show/cadastrar")
	public String cadastrar(@Valid Showw show, BindingResult result, Model model, RedirectAttributes attributes) {
		attributes.addAttribute("sucesso", "Show cadastrado com sucesso");
		if (result.hasErrors()) {
			//model.addAttribute("show", show);
			return "show/formulario";
		} else {
			showRepository.save(show);
		}
		return "redirect:/";
	}

	@GetMapping("/show/detalhe/{id}")
	public String detalheShow(@PathVariable("id") Long id, Model model) {
		Showw showBuscado = showRepository.findById(id).get();
		model.addAttribute("show", showBuscado);
		return "show/detalhe";
	}

}
