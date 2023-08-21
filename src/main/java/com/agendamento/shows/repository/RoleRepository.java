package com.agendamento.shows.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.shows.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
