package com.agendamento.shows;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agendamento.shows.service.CriptografiaService;

@ExtendWith(MockitoExtension.class)
public class CriptografiaTest {

	@InjectMocks
	CriptografiaService criptografiaService;

	@Test
	void deveGerarCorretamenteCpfCriptografado() {
		String textoDecriptado;
		String textoPuro = "";
		try {
			if (System.getenv("cpfTeste") == null) {
				throw new NullPointerException("cpfTeste é nulo!@");
			} else {
				textoPuro = System.getenv("cpfTeste");
			}
			String encriptar = criptografiaService.encriptar(textoPuro);
			textoDecriptado = criptografiaService.decriptar(encriptar);
			assertEquals(textoPuro, textoDecriptado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
