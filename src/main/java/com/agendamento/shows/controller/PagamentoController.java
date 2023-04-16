package com.agendamento.shows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/* 
 * Cartão de teste:
 * 5031 4332 1540 6351	123 11/25
 * 
 * */

@Controller
@RequestMapping("pagamento")
public class PagamentoController {

	@GetMapping("/checkout-pro")
	public String checkoutPro() {
		System.out.println("acionando checkout pro...");
		return "redirect:/";
	}

}
