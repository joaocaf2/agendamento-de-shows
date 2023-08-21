package com.agendamento.shows.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
		System.out.println("Email: " + username + " Senha: " + password);
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		return "login";
	}

}
