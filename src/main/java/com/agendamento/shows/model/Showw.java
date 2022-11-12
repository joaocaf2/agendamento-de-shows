package com.agendamento.shows.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Showw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String imagemShow;

	public Showw() {

	}

	public Showw(String nome, String descricao, BigDecimal valor) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}
	public Showw(String nome, String descricao, BigDecimal valor, String imagemShow) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.imagemShow = imagemShow;
	}

	public String getImagemShow() {
		return imagemShow;
	}

	public void setImagemShow(String imagemShow) {
		this.imagemShow = imagemShow;
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
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
