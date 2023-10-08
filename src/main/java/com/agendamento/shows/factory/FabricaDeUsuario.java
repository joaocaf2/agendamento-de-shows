package com.agendamento.shows.factory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.agendamento.shows.model.Usuario;

@Component
public class FabricaDeUsuario {

	public Usuario comEmailESenha(String email, String senha) {
		return new Usuario(email, new BCryptPasswordEncoder().encode(senha));
	}

}
