package com.agendamento.shows.dto;

public class PixInformationDTO {
	BasicPaymentInfoDTO basicInformationDTO;
	String qrCodeBase64;

	public String getQrCodeBase64() {
		return qrCodeBase64;
	}

	public void setQrCodeBase64(String qrCodeBase64) {
		this.qrCodeBase64 = qrCodeBase64;
	}

	public void imprimeInformacoesPagamentoPorPix() {
		System.out.println("== Informacoes ==");
		System.out.println("Valor da compra: " + this.getBasicInformationDTO().getTransactionAmount());
		System.out.println("Type: " + this.getBasicInformationDTO().getPayer().getIdentification().getType());
		System.out.println("Descrição da compra: " + this.getBasicInformationDTO().getDescription());
		System.out.println("Email pagador: " + this.getBasicInformationDTO().getPayer().getEmail());
		System.out.println(
				"Numero documento: " + this.getBasicInformationDTO().getPayer().getIdentification().getNumber());
		System.out.println("Tipo documento: " + this.getBasicInformationDTO().getPayer().getIdentification().getType());
		System.out.println("Payment Method ID: " + this.getBasicInformationDTO().getPaymentMethodId());
		System.out.println("Transaction Amount: " + this.getBasicInformationDTO().getTransactionAmount());
		System.out.println("==================");
	}

	@Override
	public String toString() {
		return String.format("Valor da compra: %s", this.getBasicInformationDTO().getTransactionAmount());
	}

	public BasicPaymentInfoDTO getBasicInformationDTO() {
		return basicInformationDTO;
	}

	public void setBasicInformationDTO(BasicPaymentInfoDTO basicInformationDTO) {
		this.basicInformationDTO = basicInformationDTO;
	}

}
