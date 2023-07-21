package com.agendamento.shows.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.agendamento.shows.model.Usuario;
import com.agendamento.shows.repository.UsuarioRepository;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findById(username).get();
		if (user == null) {
			throw new UsernameNotFoundException("usuario nao encontrado");
		}
		if (!user.isEnabled()) {
			throw new UsernameNotFoundException("usuario nao encontrado");
		}
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}

}
