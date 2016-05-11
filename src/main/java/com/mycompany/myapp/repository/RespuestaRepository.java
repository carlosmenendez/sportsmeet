package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Respuesta;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Respuesta entity.
 */
public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {

    @Query("select respuesta from Respuesta respuesta where respuesta.user.login = ?#{principal.username}")
    List<Respuesta> findByUserIsCurrentUser();

}
