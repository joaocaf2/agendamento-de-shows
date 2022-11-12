package com.agendamento.shows.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.shows.model.Showw;

public interface ShowRepository extends JpaRepository<Showw, Long> {

}
