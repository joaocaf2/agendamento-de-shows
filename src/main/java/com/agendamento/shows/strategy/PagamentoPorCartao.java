package com.agendamento.shows.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.agendamento.shows.dto.CardPaymentDTO;
import com.agendamento.shows.model.CarrinhoDeCompras;
import com.agendamento.shows.model.Showw;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentAdditionalInfoRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentItemRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

@Component
public class PagamentoPorCartao implements PagamentoStrategy {

	private CardPaymentDTO cardPaymentDTO;

	private CarrinhoDeCompras carrinhoDeCompras;

	public PagamentoPorCartao() {

	}

	public PagamentoPorCartao(CardPaymentDTO cardPaymentDTO, CarrinhoDeCompras carrinhoDeCompras) {
		this.cardPaymentDTO = cardPaymentDTO;
		this.carrinhoDeCompras = carrinhoDeCompras;
	}

	@Override
	public Payment pagar() {
		ArrayList<PaymentItemRequest> itens = new ArrayList<PaymentItemRequest>();
		cardPaymentDTO.imprimeInformacoesCardPayment();
		Integer qtdParcelas = cardPaymentDTO.getInstallments();
		String cpf = cardPaymentDTO.getPayer().getIdentification().getNumber();
		String type = cardPaymentDTO.getPayer().getIdentification().getType();
		IdentificationRequest identification = IdentificationRequest.builder().type(type).number(cpf).build();
		PaymentPayerRequest pagador = PaymentPayerRequest.builder().email("kaoaoao@hotmail.com")
				.identification(identification).build();
		List<Showw> showsDoCarrinho = carrinhoDeCompras.getShowsDoCarrinho();
		for (Showw show : showsDoCarrinho) {
			PaymentItemRequest itemRequest = PaymentItemRequest.builder().quantity(1).description(show.getDescricao())
					.title(show.getNome()).unitPrice(show.getValorIngresso()).pictureUrl(show.getPosterShow()).build();
			itens.add(itemRequest);
		}
		PaymentAdditionalInfoRequest informacoesAdicionais = PaymentAdditionalInfoRequest.builder().items(itens)
				.build();

		PaymentClient paymentClient = new PaymentClient();
		PaymentCreateRequest pagamento = PaymentCreateRequest.builder()
				.transactionAmount(carrinhoDeCompras.getTotalDoCarrinhoDeCompras())
				.additionalInfo(informacoesAdicionais).description(cardPaymentDTO.getProductDescription())
				.token(cardPaymentDTO.getToken()).installments(qtdParcelas)
				.paymentMethodId(cardPaymentDTO.getPaymentMethodId()).payer(pagador).build();
		Payment pagamentoGerado = null;
		try {
			pagamentoGerado = paymentClient.create(pagamento);
			return pagamentoGerado;
		} catch (MPException | MPApiException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return pagamentoGerado;
	}

}
