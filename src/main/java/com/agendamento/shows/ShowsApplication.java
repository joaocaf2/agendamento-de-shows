package com.agendamento.shows;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agendamento.shows.factory.FabricaDeUsuario;
import com.agendamento.shows.model.Role;
import com.agendamento.shows.model.Showw;
import com.agendamento.shows.model.ShowwBuilder;
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
	private FabricaDeUsuario fabricaDeUsuario;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${mpAccessToken}")
	private String mercadoPagoAccessToken;

	public static void main(String[] args) {
		SpringApplication.run(ShowsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populaShowsIniciaisCasoNecessario();
		if (verificaSeNaoExisteUsuariosNoBd()) {
			criaUsuarioInicialDoBd("adm", System.getenv("passwdAdm"), "ADM");
			criaUsuarioInicialDoBd("usuario", "1234x", "USER");
		}
		setaTokensDeConfiguracaoMP();
	}

	private void setaTokensDeConfiguracaoMP() {
		MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
	}

	private void populaShowsIniciaisCasoNecessario() {
		if (verificaSeNaoExistemShowsNoBd()) {
			Showw show = ShowwBuilder.builder().nome("Metalica").descricao("Vai acontecer em Florianópolis")
					.valorIngresso(new BigDecimal("800"))
					.posterShow("https://upload.wikimedia.org/wikipedia/commons/b/b7/Metallica_logo.png").build();
			showRepository.save(show);
			show = ShowwBuilder.builder().nome("A7x").descricao("Show a7x em SP").valorIngresso(new BigDecimal("500"))
					.posterShow("https://i.etsystatic.com/6608809/r/il/921fcd/3555531381/il_794xN.3555531381_nbdc.jpg")
					.build();
			showRepository.save(show);
		}
	}

	private void criaUsuarioInicialDoBd(String email, String senha, String nomeRole) {
		Usuario usuario = fabricaDeUsuario.comEmailESenha(email, senha);
		System.out.println("Cadastrando usuario inicial... " + usuario.getEmail());
		Role role = new Role();
		role.setNome(nomeRole);
		roleRepository.save(role);
		usuario.setRoles(Arrays.asList(role));
		usuarioRepository.save(usuario);
	}

	private boolean verificaSeNaoExistemShowsNoBd() {
		return showRepository.findAll().isEmpty();
	}

	private boolean verificaSeNaoExisteUsuariosNoBd() {
		return usuarioRepository.findAll().isEmpty();
	}

}
