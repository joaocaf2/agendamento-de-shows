package com.agendamento.shows;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.agendamento.shows.model.Role;
import com.agendamento.shows.model.Showw;
import com.agendamento.shows.model.Usuario;
import com.agendamento.shows.repository.RoleRepository;
import com.agendamento.shows.repository.ShowRepository;
import com.agendamento.shows.repository.UsuarioRepository;
import com.mercadopago.MercadoPagoConfig;

@SpringBootApplication
public class ShowsApplication implements CommandLineRunner {

	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${mercadopago_access_token}")
	private String mercadoPagoAccessToken;

	public static void main(String[] args) {
		SpringApplication.run(ShowsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populaShowsIniciaisCasoNecessario();
		if (verificaSeNaoExisteUsuarioAdmNoBd()) {
			criaOUsuarioAdminInicialSeNecessario();
		}
		setaTokensDeConfiguracaoMP();
	}

	private void setaTokensDeConfiguracaoMP() {
		MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
	}

	private void populaShowsIniciaisCasoNecessario() {
		if (verificaSeNaoExistemShowsNoBd()) {
			Showw show = criaUmShow("Metalica", "Vai acontecer em Florianópolis", new BigDecimal("900.50"),
					"https://upload.wikimedia.org/wikipedia/commons/b/b7/Metallica_logo.png");
			showRepository.save(show);
			show = criaUmShow("A7x", "Show Avenged Sevenfold em SP", new BigDecimal("1200.0"),
					"https://i.etsystatic.com/6608809/r/il/921fcd/3555531381/il_794xN.3555531381_nbdc.jpg");
			showRepository.save(show);
		}
	}

	private void criaOUsuarioAdminInicialSeNecessario() {
		System.out.println("cadastrando usuario adm...");
		Usuario usuario = new Usuario();
		usuario.setEmail("admin");
		usuario.setSenha(new BCryptPasswordEncoder().encode(System.getenv("passwdAdm")));
		Role role = new Role();
		role.setNome("ADM");
		roleRepository.save(role);
		usuario.setRoles(Arrays.asList(role));
		usuarioRepository.save(usuario);
	}

	private boolean verificaSeNaoExistemShowsNoBd() {
		return showRepository.findAll().isEmpty();
	}

	private boolean verificaSeNaoExisteUsuarioAdmNoBd() {
		return usuarioRepository.findAll().isEmpty();
	}

	private Showw criaUmShow(String nome, String descricao, BigDecimal valorIngresso, String posterShow) {
		Showw show = new Showw();
		show.setNome(nome);
		show.setDescricao(descricao);
		show.setValorIngresso(valorIngresso);
		show.setPosterShow(posterShow);
		return show;
	}

}
