package com.agendamento.shows.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;

@Controller
public class HomeController {

	@Autowired
	private ShowRepository showRepository;

	@RequestMapping("/")
	public String home(Model model) {
		List<Showw> shows = showRepository.findAll();
		model.addAttribute("shows", shows);
		return "index";
	}
}
