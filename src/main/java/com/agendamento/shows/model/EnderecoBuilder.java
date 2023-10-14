package com.agendamento.shows.model;

public class EnderecoBuilder {

	private Endereco endereco;

	private EnderecoBuilder() {
		endereco = new Endereco();
	}

	public static EnderecoBuilder builder() {
		return new EnderecoBuilder();
	}

	public EnderecoBuilder rua(String rua) {
		this.endereco.setRua(rua);
		return this;
	}

	public EnderecoBuilder bairro(String bairro) {
		this.endereco.setBairro(bairro);
		return this;
	}

	public EnderecoBuilder cep(String cep) {
		this.endereco.setCep(cep);
		return this;
	}

	public Endereco build() {
		return this.endereco;
	}

}
