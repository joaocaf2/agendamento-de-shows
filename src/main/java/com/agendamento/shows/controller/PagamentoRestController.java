package com.agendamento.shows.controller;

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
import com.agendamento.shows.dto.PixInformationDTO;
import com.agendamento.shows.model.CarrinhoDeCompras;
import com.agendamento.shows.strategy.PagamentoPorCartao;
import com.agendamento.shows.strategy.PagamentoPorPix;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

@RestController
@RequestMapping(value = "pagamento-rest")
public class PagamentoRestController {

	@Autowired
	private CarrinhoDeCompras carrinhoDeCompras;

	@Autowired
	private PagamentoPorCartao pagamentoPorCartao;
	@Autowired
	private PagamentoPorPix pagamentoPorPix;

	@PostMapping("/pagar-por-cartao")
	@ResponseBody
	public ResponseEntity<PaymentResponseDTO> formularioPagamento(@RequestBody CardPaymentDTO cardPaymentDTO,
			HttpServletRequest request) throws MPException {
		pagamentoPorCartao = new PagamentoPorCartao(cardPaymentDTO, carrinhoDeCompras);
		Payment pagamentoGerado = pagamentoPorCartao.pagar();
		Long idPagamento = pagamentoGerado.getId();
		String statusDetail = pagamentoGerado.getStatusDetail();
		String status = pagamentoGerado.getStatus();
		PaymentResponseDTO pagamentoRespostaDTO;
		pagamentoRespostaDTO = new PaymentResponseDTO(String.valueOf(idPagamento), status, statusDetail);

		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoRespostaDTO);
	}

	@PostMapping("/pagar-por-pix")
	@ResponseBody
	public ResponseEntity<PaymentResponseDTO> pagarPorPix(@RequestBody PixInformationDTO pixInformation,
			HttpServletRequest request) throws MPException {
		pagamentoPorPix = new PagamentoPorPix(pixInformation, carrinhoDeCompras);
		Payment pagamentoGerado = pagamentoPorPix.pagar();
		Long idPagamento = pagamentoGerado.getId();
		String statusDetail = pagamentoGerado.getStatusDetail();
		String status = pagamentoGerado.getStatus();
		PaymentResponseDTO pagamentoRespostaDTO;
		pagamentoRespostaDTO = new PaymentResponseDTO(String.valueOf(idPagamento), status, statusDetail);

		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoRespostaDTO);
	}

}
