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
		if (showRepository.findAll().isEmpty()) {
			Showw show = new Showw();
			show.setNome("Gun n roses");
			show.setDescricao("Vai acontecer no RIo");
			show.setValorIngresso(new BigDecimal("600.50"));
			show.setImagemShow("https://i.scdn.co/image/ab6761610000e5eb50defaf9fc059a1efc541f4c");
			showRepository.save(show);

			show = new Showw();
			show.setNome("Metalica");
			show.setDescricao("Vai acontecer em Florianópolis");
			show.setValorIngresso(new BigDecimal("900.50"));
			show.setImagemShow("https://upload.wikimedia.org/wikipedia/commons/b/b7/Metallica_logo.png");
			showRepository.save(show);

			show = new Showw();

			show.setNome("Avenged Sevenfold");
			show.setDescricao("Vai acontecer em SP");
			show.setValorIngresso(new BigDecimal("450.75"));
			show.setImagemShow("https://i.etsystatic.com/6608809/r/il/921fcd/3555531381/il_794xN.3555531381_nbdc.jpg");
			showRepository.save(show);
		}
	}

}
