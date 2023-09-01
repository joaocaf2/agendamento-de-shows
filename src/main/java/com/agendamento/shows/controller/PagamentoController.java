package com.agendamento.shows.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

/* 
 * Cartão de teste:
 * 5031 4332 1540 6351	123 11/25
 * 
 * */

@Controller
@RequestMapping("pagamento")
public class PagamentoController {

	@Autowired
	private ShowRepository showRepository;

	@GetMapping("/checkout-pro")
	public String checkoutPro() {
		return "redirect:/pagamento/checkoutproform";
	}

	@PostMapping("/checkoutproform")
	public String checkoutProForm(Model model, Showw show) throws MPException, MPApiException {
		show = buscaOShowASerComprado(show);
		PreferenceRequest preferenceRequest = criaAPreferenceRequestdoMP(show);
		PreferenceClient client = new PreferenceClient();
		Preference preference = client.create(preferenceRequest);
		model.addAttribute("preferenceId", preference.getId());
		return "pagamento/checkoutpro";
	}

	private PreferenceRequest criaAPreferenceRequestdoMP(Showw show) {
		PreferenceItemRequest itemRequest = PreferenceItemRequest.builder().id("1234").title(show.getNome())
				.description(show.getDescricao()).pictureUrl(show.getPosterShow()).categoryId("shows").quantity(1)
				.currencyId("BRL").unitPrice(show.getValorIngresso()).build();
		List<PreferenceItemRequest> items = new ArrayList<>();
		items.add(itemRequest);
		PreferenceRequest preferenceRequest = PreferenceRequest.builder().items(items).build();
		return preferenceRequest;
	}

	private Showw buscaOShowASerComprado(Showw show) {
		show = showRepository.findById(show.getId()).get();
		return show;
	}

}
