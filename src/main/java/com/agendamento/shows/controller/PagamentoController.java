package com.agendamento.shows.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendamento.shows.model.CarrinhoDeCompras;
import com.agendamento.shows.model.CarrinhoItem;
import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

/* 
 * Cartão de teste:
 * 5031 4332 1540 6351	
 * 123 
 * 11/25
 * 12345678909 
 * */

@Controller
@RequestMapping("pagamento")
public class PagamentoController {

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private CarrinhoDeCompras carrinhoDeCompras;

	@Value("${mpPublicKey}")
	private String mpPublicKey;

	@GetMapping("/checkout-pro")
	public String checkoutPro() {
		return "redirect:/pagamento/checkoutproform";
	}

	@PostMapping("/checkout-transparente")
	public String checkoutTransparenteForm(Showw show, Model model) {
		model.addAttribute("mpPublicKey", mpPublicKey);
		model.addAttribute("totalDaCompra", carrinhoDeCompras.getTotalDoCarrinhoDeCompras());
		return "/pagamento/checkout-transparente";
	}

	@PostMapping
	public String gerar(HttpServletRequest req) {
		System.out.println("chamando gerar...");
		System.out.println(req.getAttribute("token"));
		return "/";
	}

	@PostMapping("/centralpagamentoform")
	public String centralPagamentoForm(Model model, Showw show) throws MPException, MPApiException {
		show = buscaOShowASerComprado(show);
		PreferenceRequest preferenceRequest = criaAPreferenceRequestdoMP(show);
		PreferenceClient client = new PreferenceClient();
		Preference preference = client.create(preferenceRequest);
		model.addAttribute("preferenceId", preference.getId());
		model.addAttribute("show", show);
		carrinhoDeCompras.adicionaAoCarrinho(new CarrinhoItem(show));
		return "pagamento/central-de-pagamentos";
	}

	private PreferenceRequest criaAPreferenceRequestdoMP(Showw show) {
		PreferenceItemRequest itemDaCompra = PreferenceItemRequest.builder().id(String.valueOf(show.getId()))
				.title(show.getNome()).description(show.getDescricao()).pictureUrl(show.getPosterShow())
				.categoryId("shows").quantity(1).currencyId("BRL").unitPrice(show.getValorIngresso()).build();
		List<PreferenceItemRequest> items = new ArrayList<>();
		items.add(itemDaCompra);
		PreferenceRequest preferenceRequest = PreferenceRequest.builder().items(items).build();
		return preferenceRequest;
	}

	private Showw buscaOShowASerComprado(Showw show) {
		show = showRepository.findById(show.getId()).get();
		return show;
	}

}
