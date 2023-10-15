package com.agendamento.shows.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CarrinhoItem {
	private Showw show;

	public CarrinhoItem(Showw show) {
		this.show = show;
	}

	public Showw getShow() {
		return show;
	}

	public void setShow(Showw show) {
		this.show = show;
	}

	public BigDecimal getTotalCarrinhoItem(int quantidade) {
		return this.show.getValorIngresso().multiply(new BigDecimal(quantidade));
	}

	@Override
	public int hashCode() {
		return Objects.hash(show);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		return Objects.equals(show, other.show);
	}

}
