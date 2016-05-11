package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Pregunta;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pregunta entity.
 */
public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {

    @Query("select pregunta from Pregunta pregunta where pregunta.user.login = ?#{principal.username}")
    List<Pregunta> findByUserIsCurrentUser();

}
