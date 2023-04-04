package com.agendamento.shows.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String formulario() {
		return "show/formulario";
	}

	@PostMapping("/show/cadastrar")
	public String cadastrar(Showw show, RedirectAttributes attributes) {
		System.out.println("Show: " + show.getNome() + " " + show.getDescricao() + show.getImagemShow());
		attributes.addAttribute("sucesso", "Show cadastrado com sucesso");
		showRepository.save(show);
		return "redirect:/";
	}

	@GetMapping("/show/detalhe/{id}")
	public String detalheShow(@PathVariable("id") Long id) {
		return "show/detalhe";
	}

}
