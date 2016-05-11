package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Deportefavorito;
import com.mycompany.myapp.repository.DeportefavoritoRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Deportefavorito.
 */
@RestController
@RequestMapping("/api")
public class DeportefavoritoResource {

    private final Logger log = LoggerFactory.getLogger(DeportefavoritoResource.class);
        
    @Inject
    private DeportefavoritoRepository deportefavoritoRepository;
    
    /**
     * POST  /deportefavoritos : Create a new deportefavorito.
     *
     * @param deportefavorito the deportefavorito to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deportefavorito, or with status 400 (Bad Request) if the deportefavorito has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/deportefavoritos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deportefavorito> createDeportefavorito(@RequestBody Deportefavorito deportefavorito) throws URISyntaxException {
        log.debug("REST request to save Deportefavorito : {}", deportefavorito);
        if (deportefavorito.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("deportefavorito", "idexists", "A new deportefavorito cannot already have an ID")).body(null);
        }
        Deportefavorito result = deportefavoritoRepository.save(deportefavorito);
        return ResponseEntity.created(new URI("/api/deportefavoritos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("deportefavorito", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /deportefavoritos : Updates an existing deportefavorito.
     *
     * @param deportefavorito the deportefavorito to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deportefavorito,
     * or with status 400 (Bad Request) if the deportefavorito is not valid,
     * or with status 500 (Internal Server Error) if the deportefavorito couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/deportefavoritos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deportefavorito> updateDeportefavorito(@RequestBody Deportefavorito deportefavorito) throws URISyntaxException {
        log.debug("REST request to update Deportefavorito : {}", deportefavorito);
        if (deportefavorito.getId() == null) {
            return createDeportefavorito(deportefavorito);
        }
        Deportefavorito result = deportefavoritoRepository.save(deportefavorito);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("deportefavorito", deportefavorito.getId().toString()))
            .body(result);
    }

    /**
     * GET  /deportefavoritos : get all the deportefavoritos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deportefavoritos in body
     */
    @RequestMapping(value = "/deportefavoritos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Deportefavorito> getAllDeportefavoritos() {
        log.debug("REST request to get all Deportefavoritos");
        List<Deportefavorito> deportefavoritos = deportefavoritoRepository.findAll();
        return deportefavoritos;
    }

    /**
     * GET  /deportefavoritos/:id : get the "id" deportefavorito.
     *
     * @param id the id of the deportefavorito to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deportefavorito, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/deportefavoritos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deportefavorito> getDeportefavorito(@PathVariable Long id) {
        log.debug("REST request to get Deportefavorito : {}", id);
        Deportefavorito deportefavorito = deportefavoritoRepository.findOne(id);
        return Optional.ofNullable(deportefavorito)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /deportefavoritos/:id : delete the "id" deportefavorito.
     *
     * @param id the id of the deportefavorito to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/deportefavoritos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDeportefavorito(@PathVariable Long id) {
        log.debug("REST request to delete Deportefavorito : {}", id);
        deportefavoritoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("deportefavorito", id.toString())).build();
    }

}
