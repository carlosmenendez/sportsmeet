package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Deporte;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Deporte entity.
 */
public interface DeporteRepository extends JpaRepository<Deporte,Long> {

}
