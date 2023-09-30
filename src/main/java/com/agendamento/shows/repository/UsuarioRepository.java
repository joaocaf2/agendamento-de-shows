package com.agendamento.shows.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.shows.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByEmail(String email);
}
