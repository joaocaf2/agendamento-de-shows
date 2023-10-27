package com.agendamento.shows.strategy;

import com.mercadopago.resources.payment.Payment;

public interface PagamentoStrategy {
	Payment pagar();
}
