package com.agendamento.shows.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercadopago.MercadoPagoConfig;

/* 
 * Cartão de teste:
 * 5031 4332 1540 6351	123 11/25
 * 
 * */

@Controller
@RequestMapping("pagamento")
public class PagamentoController {

	@Value("${mercadoPagoAccessToken}")
	private String mercadoPagoAccessToken;

	@GetMapping("/checkout-pro")
	public String checkoutPro() {
		return "redirect:/pagamento/checkoutproform";
	}

	@GetMapping("/checkoutproform")
	public String checkoutProForm() {
		System.out.println(mercadoPagoAccessToken);
		MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
		return "pagamento/checkoutpro";
	}

}
