package com.agendamento.shows;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agendamento.shows.model.CarrinhoDeCompras;
import com.agendamento.shows.model.CarrinhoItem;
import com.agendamento.shows.model.Showw;
import com.agendamento.shows.model.ShowwBuilder;

@ExtendWith(MockitoExtension.class)
public class CarrinhoDeComprasTest {

	@InjectMocks
	private CarrinhoDeCompras carrinhoDeCompras;

	@Test
	public void temQueAdicionarERemoverUmItemDoCarrinhoCorretamente() {
		Showw show = ShowwBuilder.builder().nome("show teste").descricao("qualquer desc").build();
		CarrinhoItem item = new CarrinhoItem(show);
		carrinhoDeCompras.adicionaAoCarrinho(item);
		assertEquals(carrinhoDeCompras.getQuantidadeDoCarrinhoItem(item), 1);
		carrinhoDeCompras.removeDoCarrinho(show);
		assertTrue(carrinhoDeCompras.verificaSeCarrinhoEstaVazio());
	}

	@Test
	public void verificaSeAdicionaItemNaQuantidadeCerta() {
		Showw show = ShowwBuilder.builder().nome("show teste").descricao("qualquer desc").build();
		CarrinhoItem item = new CarrinhoItem(show);
		carrinhoDeCompras.adicionaAoCarrinho(item);
		carrinhoDeCompras.adicionaAoCarrinho(item);
		assertEquals(1, carrinhoDeCompras.getItens().size());
		assertEquals(2, carrinhoDeCompras.getQuantidadeDoCarrinhoItem(item));
	}

	@Test
	public void temQueCalcularAQuantidadeDoCarrinhoCorretamente() {
		Showw show = ShowwBuilder.builder().nome("show teste").descricao("qualquer desc")
				.valorIngresso(new BigDecimal("20")).build();

		Showw show2 = ShowwBuilder.builder().nome("show teste2").descricao("qualquer desc2")
				.valorIngresso(new BigDecimal("10")).build();

		Showw show3 = ShowwBuilder.builder().nome("show teste3").descricao("qualquer desc3")
				.valorIngresso(new BigDecimal("30")).build();
		CarrinhoItem item = new CarrinhoItem(show);
		carrinhoDeCompras.adicionaAoCarrinho(new CarrinhoItem(show));
		carrinhoDeCompras.adicionaAoCarrinho(new CarrinhoItem(show2));
		carrinhoDeCompras.adicionaAoCarrinho(new CarrinhoItem(show3));

		List<Showw> shows = new ArrayList<Showw>();
		Collections.addAll(shows, show, show2, show3);
		BigDecimal valorShows = BigDecimal.ZERO;
		for (Showw showw : shows) {
			valorShows = valorShows.add(showw.getValorIngresso());
		}
		assertEquals(valorShows, carrinhoDeCompras.getTotalDoCarrinhoDeCompras());
	}

}
