package com.agendamento.shows.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShowwBuilder {

	private Showw show;

	private ShowwBuilder() {
		show = new Showw();
	}

	public static ShowwBuilder builder() {
		return new ShowwBuilder();
	}

	public ShowwBuilder nome(String nome) {
		this.show.setNome(nome);
		return this;
	}

	public ShowwBuilder descricao(String descricao) {
		this.show.setDescricao(descricao);
		return this;
	}

	public ShowwBuilder valorIngresso(BigDecimal valorIngresso) {
		this.show.setValorIngresso(valorIngresso);
		return this;
	}


	public ShowwBuilder dataShow(LocalDate dataShow) {
		this.show.setDataShow(dataShow);
		return this;
	}

	public ShowwBuilder posterShow(String posterShow) {
		this.show.setPosterShow(posterShow);
		return this;
	}

	public Showw build() {
		return this.show;
	}

}
