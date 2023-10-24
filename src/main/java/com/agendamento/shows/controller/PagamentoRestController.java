package com.agendamento.shows.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.shows.dto.CardPaymentDTO;
import com.agendamento.shows.dto.PaymentResponseDTO;
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

@RestController
@RequestMapping(value = "pagamento-rest")
public class PagamentoRestController {

	@Autowired
	private CarrinhoDeCompras carrinhoDeCompras;

	@PostMapping("/gerar")
	@ResponseBody
	public ResponseEntity<PaymentResponseDTO> formularioPagamento(@RequestBody CardPaymentDTO cardPaymentDTO,
			HttpServletRequest request) throws MPException {
		ArrayList<PaymentItemRequest> itens = new ArrayList<PaymentItemRequest>();
		imprimeInformacoesCardPayment(cardPaymentDTO);
		Integer qtdParcelas = cardPaymentDTO.getInstallments();
		String cpf = cardPaymentDTO.getPayer().getIdentification().getNumber();
		String type = cardPaymentDTO.getPayer().getIdentification().getType();
		IdentificationRequest identification = IdentificationRequest.builder().type(type).number(cpf).build();
		PaymentPayerRequest pagador = PaymentPayerRequest.builder().email(cardPaymentDTO.getPayer().getEmail())
				.identification(identification).build();
		List<Showw> showsDoCarrinho = carrinhoDeCompras.getShowsDoCarrinho();
		for (Showw show : showsDoCarrinho) {
			PaymentItemRequest itemRequest = PaymentItemRequest.builder().quantity(1).description(show.getDescricao())
					.title(show.getNome()).unitPrice(show.getValorIngresso()).pictureUrl(show.getPosterShow()).build();
			itens.add(itemRequest);
		}
		PaymentAdditionalInfoRequest informacoesAdicionais = PaymentAdditionalInfoRequest.builder().items(itens).build();

		PaymentClient paymentClient = new PaymentClient();
		PaymentCreateRequest pagamento = PaymentCreateRequest.builder()
				.transactionAmount(carrinhoDeCompras.getTotalDoCarrinhoDeCompras()).additionalInfo(informacoesAdicionais)
				.token(cardPaymentDTO.getToken()).description(cardPaymentDTO.getProductDescription())
				.installments(qtdParcelas).paymentMethodId(cardPaymentDTO.getPaymentMethodId()).payer(pagador).build();
		PaymentResponseDTO pagamentoRespostaDTO = new PaymentResponseDTO();
		try {
			Payment pagamentoGerado = paymentClient.create(pagamento);
			Long idPagamento = pagamentoGerado.getId();
			String statusDetail = pagamentoGerado.getStatusDetail();
			String status = pagamentoGerado.getStatus();
			pagamentoRespostaDTO = new PaymentResponseDTO(String.valueOf(idPagamento), status, statusDetail);
		} catch (MPException | MPApiException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoRespostaDTO);
	}


	private void imprimeInformacoesCardPayment(CardPaymentDTO cardPaymentDTO) {
		System.out.println("== Informacoes ==");
		System.out.println(cardPaymentDTO.getInstallments());
		System.out.println(cardPaymentDTO.getTransactionAmount());
		System.out.println(cardPaymentDTO.getToken());
		System.out.println(cardPaymentDTO.getPayer().getIdentification().getType());
		System.out.println(cardPaymentDTO.getProductDescription());
		System.out.println(cardPaymentDTO.getPayer().getEmail());
		System.out.println(cardPaymentDTO.getPayer().getIdentification().getNumber());
		System.out.println(cardPaymentDTO.getPayer().getIdentification().getType());
		System.out.println("==================");
	}

}
