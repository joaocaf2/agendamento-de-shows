package com.agendamento.shows.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoDeCompras {

	private Map<CarrinhoItem, Integer> itens;

	public CarrinhoDeCompras() {
		this.itens = new LinkedHashMap<>();
	}

	public void adicionaAoCarrinho(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			this.itens.put(item, 1);
		} else {
			this.itens.put(item, itens.get(item) + 1);
		}
	}

	public void adicionaAoCarrinho(CarrinhoItem item, Integer quantidade) {
		this.itens.put(item, quantidade);
	}

	public void removeDoCarrinho(Showw show) {
		this.itens.remove(new CarrinhoItem(show));
	}

	public int getQuantidadeDoCarrinhoItem(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			this.itens.put(item, 0);
		}
		return this.itens.get(item);
	}

	public BigDecimal getTotalDoCarrinhoDeCompras() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(item.getTotalCarrinhoItem(getQuantidadeDoCarrinhoItem(item)));
		}
		return total;
	}

	public void esvaziaOCarrinho() {
		this.itens.clear();
	}

	public boolean verificaSeCarrinhoEstaVazio() {
		return itens.size() == 0;
	}

	public void setItens(Map<CarrinhoItem, Integer> itens) {
		this.itens = itens;
	}

	public Map<CarrinhoItem, Integer> getItens() {
		return itens;
	}

	public List<Showw> getShowsDoCarrinho() {
		Set<CarrinhoItem> keySet = itens.keySet();
		List<CarrinhoItem> itensCarrinho = new ArrayList<CarrinhoItem>(keySet);
		List<Showw> shows = new ArrayList<Showw>();
		for (CarrinhoItem carrinhoItem : itensCarrinho) {
			shows.add(carrinhoItem.getShow());
		}
		return shows;
	}

}
