package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Deportefavorito;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Deportefavorito entity.
 */
public interface DeportefavoritoRepository extends JpaRepository<Deportefavorito,Long> {

    @Query("select deportefavorito from Deportefavorito deportefavorito where deportefavorito.user.login = ?#{principal.username}")
    List<Deportefavorito> findByUserIsCurrentUser();

}
