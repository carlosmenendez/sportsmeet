package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Evento;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Evento entity.
 */
public interface EventoRepository extends JpaRepository<Evento,Long> {

    @Query("select evento from Evento evento where evento.user.login = ?#{principal.username}")
    List<Evento> findByUserIsCurrentUser();

}
