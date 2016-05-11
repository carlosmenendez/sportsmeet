package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Deporte;
import com.mycompany.myapp.repository.DeporteRepository;
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
 * REST controller for managing Deporte.
 */
@RestController
@RequestMapping("/api")
public class DeporteResource {

    private final Logger log = LoggerFactory.getLogger(DeporteResource.class);
        
    @Inject
    private DeporteRepository deporteRepository;
    
    /**
     * POST  /deportes : Create a new deporte.
     *
     * @param deporte the deporte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deporte, or with status 400 (Bad Request) if the deporte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/deportes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deporte> createDeporte(@RequestBody Deporte deporte) throws URISyntaxException {
        log.debug("REST request to save Deporte : {}", deporte);
        if (deporte.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("deporte", "idexists", "A new deporte cannot already have an ID")).body(null);
        }
        Deporte result = deporteRepository.save(deporte);
        return ResponseEntity.created(new URI("/api/deportes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("deporte", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /deportes : Updates an existing deporte.
     *
     * @param deporte the deporte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deporte,
     * or with status 400 (Bad Request) if the deporte is not valid,
     * or with status 500 (Internal Server Error) if the deporte couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/deportes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deporte> updateDeporte(@RequestBody Deporte deporte) throws URISyntaxException {
        log.debug("REST request to update Deporte : {}", deporte);
        if (deporte.getId() == null) {
            return createDeporte(deporte);
        }
        Deporte result = deporteRepository.save(deporte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("deporte", deporte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /deportes : get all the deportes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deportes in body
     */
    @RequestMapping(value = "/deportes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Deporte> getAllDeportes() {
        log.debug("REST request to get all Deportes");
        List<Deporte> deportes = deporteRepository.findAll();
        return deportes;
    }

    /**
     * GET  /deportes/:id : get the "id" deporte.
     *
     * @param id the id of the deporte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deporte, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/deportes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Deporte> getDeporte(@PathVariable Long id) {
        log.debug("REST request to get Deporte : {}", id);
        Deporte deporte = deporteRepository.findOne(id);
        return Optional.ofNullable(deporte)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /deportes/:id : delete the "id" deporte.
     *
     * @param id the id of the deporte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/deportes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDeporte(@PathVariable Long id) {
        log.debug("REST request to delete Deporte : {}", id);
        deporteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("deporte", id.toString())).build();
    }

}
