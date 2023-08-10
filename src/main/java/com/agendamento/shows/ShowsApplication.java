package com.agendamento.shows;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agendamento.shows.model.Showw;
import com.agendamento.shows.repository.ShowRepository;

@SpringBootApplication
public class ShowsApplication implements CommandLineRunner {

	@Autowired
	private ShowRepository showRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShowsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populaShowsCasoNecessario();
	}

	private void populaShowsCasoNecessario() {
		if (verificaSeNaoExistem()) {
			Showw show = criaUmShow("Metalica", "Vai acontecer em Florianópolis", new BigDecimal("900.50"),
					"https://upload.wikimedia.org/wikipedia/commons/b/b7/Metallica_logo.png");
			showRepository.save(show);
			show = criaUmShow("A7x", "Show Avenged Sevenfold em SP", new BigDecimal("1200.0"),
					"https://i.etsystatic.com/6608809/r/il/921fcd/3555531381/il_794xN.3555531381_nbdc.jpg");
			showRepository.save(show);
		}
	}

	private boolean verificaSeNaoExistem() {
		return showRepository.findAll().isEmpty();
	}

	private Showw criaUmShow(String nome, String descricao, BigDecimal valorIngresso, String posterShow) {
		Showw show = new Showw();
		show.setNome("Gun n roses");
		show.setDescricao("Vai acontecer no RIo");
		show.setValorIngresso(valorIngresso);
		show.setPosterShow(posterShow);
		return show;
	}

}
