package com.agendamento.shows.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendamento.shows.model.Role;
import com.agendamento.shows.model.Usuario;
import com.agendamento.shows.repository.RoleRepository;
import com.agendamento.shows.repository.UsuarioRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/novo")
	public String novo(Usuario usuario) {
		Role role = roleRepository.findById("USER").get();
		usuario.setRoles(Arrays.asList(role));
		usuarioRepository.save(usuario);
		System.out.println("Usuário salvo com sucesso!");
		return "redirect:/";
	}

	@GetMapping("/formulario/cadastrar")
	public String cadastrar(Usuario usuario) {
		return "usuario/formulario";
	}

}
