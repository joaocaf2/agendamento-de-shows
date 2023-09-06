package com.agendamento.shows.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.agendamento.shows.validator.StringMinimoCaracterOpcional;
import com.agendamento.shows.validator.TemQueSerNumero;

@Entity
public class Showw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Nome do show é obrigatório")
	private String nome;

	@StringMinimoCaracterOpcional(minimo = 50)
	@Length(max = 2048, message = "Máximo de 2048 caracteres")
	private String descricao;

	@NotNull(message = "Valor do ingresso é obrigatório")
	@TemQueSerNumero
	private BigDecimal valorIngresso;

	@URL(message = "Url inválida")
	@Column(columnDefinition = "VARCHAR(2048)")
	@Length(max = 2048)
	private String posterShow;

	@NotNull(message = "Data que o show ocorrerá é obrigatório")
	private LocalDate dataShow;

	public Showw() {

	}

	public Showw(String nome, String descricao, BigDecimal valorIngresso) {
		this.nome = nome;
		this.descricao = descricao;
		this.valorIngresso = valorIngresso;
	}

	public Showw(String nome, String descricao, BigDecimal valor, String posterShow) {
		this.nome = nome;
		this.descricao = descricao;
		this.valorIngresso = valor;
		this.posterShow = posterShow;
	}

	public String getPosterShow() {
		return this.posterShow;
	}

	public void setPosterShow(String posterShow) {
		this.posterShow = posterShow;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataShow() {
		return dataShow;
	}

	public void setDataShow(LocalDate dataShow) {
		this.dataShow = dataShow;
	}

	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valorIngresso;
	}

	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}

	@Override
	public String toString() {
		return "Show: " + getNome() + "Descrição: " + getDescricao() + " Valor Ingresso: " + getValorIngresso();
	}

}
