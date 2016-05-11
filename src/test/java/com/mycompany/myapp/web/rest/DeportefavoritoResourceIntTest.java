package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BasketballOauth2Jhipster3App;
import com.mycompany.myapp.domain.Deportefavorito;
import com.mycompany.myapp.repository.DeportefavoritoRepository;

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

import com.mycompany.myapp.domain.enumeration.NivelHabilidad;

/**
 * Test class for the DeportefavoritoResource REST controller.
 *
 * @see DeportefavoritoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasketballOauth2Jhipster3App.class)
@WebAppConfiguration
@IntegrationTest
public class DeportefavoritoResourceIntTest {


    private static final NivelHabilidad DEFAULT_NIVEL_DOMINIO = NivelHabilidad.Basico;
    private static final NivelHabilidad UPDATED_NIVEL_DOMINIO = NivelHabilidad.Intermedio;

    @Inject
    private DeportefavoritoRepository deportefavoritoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDeportefavoritoMockMvc;

    private Deportefavorito deportefavorito;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DeportefavoritoResource deportefavoritoResource = new DeportefavoritoResource();
        ReflectionTestUtils.setField(deportefavoritoResource, "deportefavoritoRepository", deportefavoritoRepository);
        this.restDeportefavoritoMockMvc = MockMvcBuilders.standaloneSetup(deportefavoritoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        deportefavorito = new Deportefavorito();
        deportefavorito.setNivelDominio(DEFAULT_NIVEL_DOMINIO);
    }

    @Test
    @Transactional
    public void createDeportefavorito() throws Exception {
        int databaseSizeBeforeCreate = deportefavoritoRepository.findAll().size();

        // Create the Deportefavorito

        restDeportefavoritoMockMvc.perform(post("/api/deportefavoritos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(deportefavorito)))
                .andExpect(status().isCreated());

        // Validate the Deportefavorito in the database
        List<Deportefavorito> deportefavoritos = deportefavoritoRepository.findAll();
        assertThat(deportefavoritos).hasSize(databaseSizeBeforeCreate + 1);
        Deportefavorito testDeportefavorito = deportefavoritos.get(deportefavoritos.size() - 1);
        assertThat(testDeportefavorito.getNivelDominio()).isEqualTo(DEFAULT_NIVEL_DOMINIO);
    }

    @Test
    @Transactional
    public void getAllDeportefavoritos() throws Exception {
        // Initialize the database
        deportefavoritoRepository.saveAndFlush(deportefavorito);

        // Get all the deportefavoritos
        restDeportefavoritoMockMvc.perform(get("/api/deportefavoritos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(deportefavorito.getId().intValue())))
                .andExpect(jsonPath("$.[*].nivelDominio").value(hasItem(DEFAULT_NIVEL_DOMINIO.toString())));
    }

    @Test
    @Transactional
    public void getDeportefavorito() throws Exception {
        // Initialize the database
        deportefavoritoRepository.saveAndFlush(deportefavorito);

        // Get the deportefavorito
        restDeportefavoritoMockMvc.perform(get("/api/deportefavoritos/{id}", deportefavorito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(deportefavorito.getId().intValue()))
            .andExpect(jsonPath("$.nivelDominio").value(DEFAULT_NIVEL_DOMINIO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeportefavorito() throws Exception {
        // Get the deportefavorito
        restDeportefavoritoMockMvc.perform(get("/api/deportefavoritos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeportefavorito() throws Exception {
        // Initialize the database
        deportefavoritoRepository.saveAndFlush(deportefavorito);
        int databaseSizeBeforeUpdate = deportefavoritoRepository.findAll().size();

        // Update the deportefavorito
        Deportefavorito updatedDeportefavorito = new Deportefavorito();
        updatedDeportefavorito.setId(deportefavorito.getId());
        updatedDeportefavorito.setNivelDominio(UPDATED_NIVEL_DOMINIO);

        restDeportefavoritoMockMvc.perform(put("/api/deportefavoritos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedDeportefavorito)))
                .andExpect(status().isOk());

        // Validate the Deportefavorito in the database
        List<Deportefavorito> deportefavoritos = deportefavoritoRepository.findAll();
        assertThat(deportefavoritos).hasSize(databaseSizeBeforeUpdate);
        Deportefavorito testDeportefavorito = deportefavoritos.get(deportefavoritos.size() - 1);
        assertThat(testDeportefavorito.getNivelDominio()).isEqualTo(UPDATED_NIVEL_DOMINIO);
    }

    @Test
    @Transactional
    public void deleteDeportefavorito() throws Exception {
        // Initialize the database
        deportefavoritoRepository.saveAndFlush(deportefavorito);
        int databaseSizeBeforeDelete = deportefavoritoRepository.findAll().size();

        // Get the deportefavorito
        restDeportefavoritoMockMvc.perform(delete("/api/deportefavoritos/{id}", deportefavorito.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Deportefavorito> deportefavoritos = deportefavoritoRepository.findAll();
        assertThat(deportefavoritos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
