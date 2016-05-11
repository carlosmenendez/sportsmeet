package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BasketballOauth2Jhipster3App;
import com.mycompany.myapp.domain.Respuesta;
import com.mycompany.myapp.repository.RespuestaRepository;

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
 * Test class for the RespuestaResource REST controller.
 *
 * @see RespuestaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasketballOauth2Jhipster3App.class)
@WebAppConfiguration
@IntegrationTest
public class RespuestaResourceIntTest {

    private static final String DEFAULT_ANSWER = "AAAAA";
    private static final String UPDATED_ANSWER = "BBBBB";

    @Inject
    private RespuestaRepository respuestaRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRespuestaMockMvc;

    private Respuesta respuesta;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RespuestaResource respuestaResource = new RespuestaResource();
        ReflectionTestUtils.setField(respuestaResource, "respuestaRepository", respuestaRepository);
        this.restRespuestaMockMvc = MockMvcBuilders.standaloneSetup(respuestaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        respuesta = new Respuesta();
        respuesta.setAnswer(DEFAULT_ANSWER);
    }

    @Test
    @Transactional
    public void createRespuesta() throws Exception {
        int databaseSizeBeforeCreate = respuestaRepository.findAll().size();

        // Create the Respuesta

        restRespuestaMockMvc.perform(post("/api/respuestas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(respuesta)))
                .andExpect(status().isCreated());

        // Validate the Respuesta in the database
        List<Respuesta> respuestas = respuestaRepository.findAll();
        assertThat(respuestas).hasSize(databaseSizeBeforeCreate + 1);
        Respuesta testRespuesta = respuestas.get(respuestas.size() - 1);
        assertThat(testRespuesta.getAnswer()).isEqualTo(DEFAULT_ANSWER);
    }

    @Test
    @Transactional
    public void getAllRespuestas() throws Exception {
        // Initialize the database
        respuestaRepository.saveAndFlush(respuesta);

        // Get all the respuestas
        restRespuestaMockMvc.perform(get("/api/respuestas?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(respuesta.getId().intValue())))
                .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER.toString())));
    }

    @Test
    @Transactional
    public void getRespuesta() throws Exception {
        // Initialize the database
        respuestaRepository.saveAndFlush(respuesta);

        // Get the respuesta
        restRespuestaMockMvc.perform(get("/api/respuestas/{id}", respuesta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(respuesta.getId().intValue()))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespuesta() throws Exception {
        // Get the respuesta
        restRespuestaMockMvc.perform(get("/api/respuestas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespuesta() throws Exception {
        // Initialize the database
        respuestaRepository.saveAndFlush(respuesta);
        int databaseSizeBeforeUpdate = respuestaRepository.findAll().size();

        // Update the respuesta
        Respuesta updatedRespuesta = new Respuesta();
        updatedRespuesta.setId(respuesta.getId());
        updatedRespuesta.setAnswer(UPDATED_ANSWER);

        restRespuestaMockMvc.perform(put("/api/respuestas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedRespuesta)))
                .andExpect(status().isOk());

        // Validate the Respuesta in the database
        List<Respuesta> respuestas = respuestaRepository.findAll();
        assertThat(respuestas).hasSize(databaseSizeBeforeUpdate);
        Respuesta testRespuesta = respuestas.get(respuestas.size() - 1);
        assertThat(testRespuesta.getAnswer()).isEqualTo(UPDATED_ANSWER);
    }

    @Test
    @Transactional
    public void deleteRespuesta() throws Exception {
        // Initialize the database
        respuestaRepository.saveAndFlush(respuesta);
        int databaseSizeBeforeDelete = respuestaRepository.findAll().size();

        // Get the respuesta
        restRespuestaMockMvc.perform(delete("/api/respuestas/{id}", respuesta.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Respuesta> respuestas = respuestaRepository.findAll();
        assertThat(respuestas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
