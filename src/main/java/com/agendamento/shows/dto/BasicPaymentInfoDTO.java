package com.agendamento.shows.dto;

import java.math.BigDecimal;

public class BasicPaymentInfoDTO {
	String paymentMethodId;
	String description;
	BigDecimal transactionAmount;
	PayerDTO payer;
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(String paymentMethodId) {
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
	public void setPayer(PayerDTO payer) {
		this.payer = payer;
	}
	
}
