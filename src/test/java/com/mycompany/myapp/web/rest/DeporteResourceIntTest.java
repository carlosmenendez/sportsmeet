package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BasketballOauth2Jhipster3App;
import com.mycompany.myapp.domain.Deporte;
import com.mycompany.myapp.repository.DeporteRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DeporteResource REST controller.
 *
 * @see DeporteResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasketballOauth2Jhipster3App.class)
@WebAppConfiguration
@IntegrationTest
public class DeporteResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";

    @Inject
    private DeporteRepository deporteRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDeporteMockMvc;

    private Deporte deporte;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DeporteResource deporteResource = new DeporteResource();
        ReflectionTestUtils.setField(deporteResource, "deporteRepository", deporteRepository);
        this.restDeporteMockMvc = MockMvcBuilders.standaloneSetup(deporteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        deporte = new Deporte();
        deporte.setNombre(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createDeporte() throws Exception {
        int databaseSizeBeforeCreate = deporteRepository.findAll().size();

        // Create the Deporte

        restDeporteMockMvc.perform(post("/api/deportes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(deporte)))
                .andExpect(status().isCreated());

        // Validate the Deporte in the database
        List<Deporte> deportes = deporteRepository.findAll();
        assertThat(deportes).hasSize(databaseSizeBeforeCreate + 1);
        Deporte testDeporte = deportes.get(deportes.size() - 1);
        assertThat(testDeporte.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllDeportes() throws Exception {
        // Initialize the database
        deporteRepository.saveAndFlush(deporte);

        // Get all the deportes
        restDeporteMockMvc.perform(get("/api/deportes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(deporte.getId().intValue())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())));
    }

    @Test
    @Transactional
    public void getDeporte() throws Exception {
        // Initialize the database
        deporteRepository.saveAndFlush(deporte);

        // Get the deporte
        restDeporteMockMvc.perform(get("/api/deportes/{id}", deporte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(deporte.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeporte() throws Exception {
        // Get the deporte
        restDeporteMockMvc.perform(get("/api/deportes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeporte() throws Exception {
        // Initialize the database
        deporteRepository.saveAndFlush(deporte);
        int databaseSizeBeforeUpdate = deporteRepository.findAll().size();

        // Update the deporte
        Deporte updatedDeporte = new Deporte();
        updatedDeporte.setId(deporte.getId());
        updatedDeporte.setNombre(UPDATED_NOMBRE);

        restDeporteMockMvc.perform(put("/api/deportes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedDeporte)))
                .andExpect(status().isOk());

        // Validate the Deporte in the database
        List<Deporte> deportes = deporteRepository.findAll();
        assertThat(deportes).hasSize(databaseSizeBeforeUpdate);
        Deporte testDeporte = deportes.get(deportes.size() - 1);
        assertThat(testDeporte.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void deleteDeporte() throws Exception {
        // Initialize the database
        deporteRepository.saveAndFlush(deporte);
        int databaseSizeBeforeDelete = deporteRepository.findAll().size();

        // Get the deporte
        restDeporteMockMvc.perform(delete("/api/deportes/{id}", deporte.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Deporte> deportes = deporteRepository.findAll();
        assertThat(deportes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
