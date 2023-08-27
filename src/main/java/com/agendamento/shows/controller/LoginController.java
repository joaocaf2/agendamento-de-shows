package com.agendamento.shows.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "erro", required = false) Integer erro, Model model, String username,
			String password, HttpServletRequest request) {
		if (erro != null && erro == 1) {
			System.out.println("Deu erro no login");
			model.addAttribute("erro",
					"Falha no login: E-mail ou senha incorretos. Ou talvez você ainda não tenha ativado sua conta.");
		}
		return "login";
	}

}
