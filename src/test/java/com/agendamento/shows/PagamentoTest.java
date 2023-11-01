package com.agendamento.shows;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agendamento.shows.dto.BasicPaymentInfoDTO;
import com.agendamento.shows.dto.PixInformationDTO;
import com.agendamento.shows.model.CarrinhoDeCompras;
import com.agendamento.shows.model.CarrinhoItem;
import com.agendamento.shows.model.Showw;
import com.agendamento.shows.model.ShowwBuilder;
import com.agendamento.shows.strategy.PagamentoPorPix;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentAdditionalInfoRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentItemRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

@ExtendWith(MockitoExtension.class)
public class PagamentoTest {

	@InjectMocks
	private CarrinhoDeCompras carrinhoDeCompras;
	@InjectMocks
	private PixInformationDTO pixInformationDTO;
	@InjectMocks
	private PagamentoPorPix pagamentoPix;
	@InjectMocks
	private BasicPaymentInfoDTO basicInformation;

	@Test
	public void temQueRealizarPagamentoPixComSucesso() {
		MercadoPagoConfig.setAccessToken(System.getenv("mpAccessToken"));
		ArrayList<PaymentItemRequest> itens = new ArrayList<PaymentItemRequest>();
		String cpf = "12345678909";
		String type = "CPF";
		IdentificationRequest identification = IdentificationRequest.builder().type(type).number(cpf).build();
		PaymentPayerRequest pagador = PaymentPayerRequest.builder().email("kaoaoao@hotmail.com")
				.identification(identification).build();
		Showw showCarrinho = ShowwBuilder.builder().nome("aoaiai").descricao("aoaoao").valorIngresso("400.50").build();
		carrinhoDeCompras.adicionaAoCarrinho(new CarrinhoItem(showCarrinho), 3);
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
				.additionalInfo(informacoesAdicionais).description("Uma descrição qualquer").paymentMethodId("pix")
				.payer(pagador).build();
		Payment pagamentoGerado = null;
		try {
			pagamentoGerado = paymentClient.create(pagamento);
			System.out.println(pagamentoGerado.getTransactionAmount());
			System.out.println(pagamentoGerado.getDescription());
			System.out.println(pagamentoGerado.getInstallments());
		} catch (MPApiException apiException) {
			System.err.println(apiException.getMessage());
			apiException.printStackTrace();
			System.out.println(apiException.getApiResponse().getContent());
		} catch (MPException mpException) {

		}
		assertNotNull(pagamentoGerado);
	}

}
