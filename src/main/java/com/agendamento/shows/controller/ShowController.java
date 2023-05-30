package com.agendamento.shows.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;

@Controller
@RequestMapping("show")
public class ShowController {

	@Autowired
	private ShowRepository showRepository;

	@GetMapping("/formulario/cadastrar")
	public String formulario(Showw show, Model model) {
		model.addAttribute("show", show);
		return "show/formulario";
	}

	@PostMapping("/novo")
	public String novo(@Valid @ModelAttribute("show") Showw show, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "show/formulario";
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
