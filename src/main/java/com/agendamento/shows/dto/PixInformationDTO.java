package com.agendamento.shows.dto;

import java.math.BigDecimal;

public class PixInformationDTO {
	String paymentMethodId;
	String description;
	BigDecimal transactionAmount;
	PayerDTO payer;
	String qrCodeBase64;

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethoId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public PayerDTO getPayer() {
		return payer;
	}

	public void setPayer(PayerDTO payerDTO) {
		this.payer = payerDTO;
	}

	public String getQrCodeBase64() {
		return qrCodeBase64;
	}

	public void setQrCodeBase64(String qrCodeBase64) {
		this.qrCodeBase64 = qrCodeBase64;
	}

	public void imprimeInformacoesPagamentoPorPix() {
		System.out.println("== Informacoes ==");
		System.out.println("Valor da compra: " + this.getTransactionAmount());
		System.out.println("Type: " + this.getPayer().getIdentification().getType());
		System.out.println("Descrição da compra: " + this.getDescription());
		System.out.println("Email pagador: " + this.getPayer().getEmail());
		System.out.println("Numero documento: " + this.getPayer().getIdentification().getNumber());
		System.out.println("Tipo documento: " + this.getPayer().getIdentification().getType());
		System.out.println("Payment Method ID: " + this.getPaymentMethodId());
		System.out.println("Transaction Amount: " + this.getTransactionAmount());
		System.out.println("==================");
	}

	@Override
	public String toString() {
		return String.format("Valor da compra: %s", this.getTransactionAmount());
	}

}
