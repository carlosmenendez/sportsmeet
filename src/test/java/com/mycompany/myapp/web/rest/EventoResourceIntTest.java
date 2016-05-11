package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BasketballOauth2Jhipster3App;
import com.mycompany.myapp.domain.Evento;
import com.mycompany.myapp.repository.EventoRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the EventoResource REST controller.
 *
 * @see EventoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasketballOauth2Jhipster3App.class)
@WebAppConfiguration
@IntegrationTest
public class EventoResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final String DEFAULT_LOCALIDAD = "AAAAA";
    private static final String UPDATED_LOCALIDAD = "BBBBB";

    private static final ZonedDateTime DEFAULT_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_HORA_STR = dateTimeFormatter.format(DEFAULT_HORA);

    private static final Double DEFAULT_INICIO_LATITUD = 1D;
    private static final Double UPDATED_INICIO_LATITUD = 2D;

    private static final Double DEFAULT_INICIO_LONGITUD = 1D;
    private static final Double UPDATED_INICIO_LONGITUD = 2D;

    private static final Double DEFAULT_FINAL_TRAYECTO_LATITUD = 1D;
    private static final Double UPDATED_FINAL_TRAYECTO_LATITUD = 2D;

    private static final Double DEFAULT_FINAL_TRAYECTO_LONGITUD = 1D;
    private static final Double UPDATED_FINAL_TRAYECTO_LONGITUD = 2D;

    @Inject
    private EventoRepository eventoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEventoMockMvc;

    private Evento evento;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EventoResource eventoResource = new EventoResource();
        ReflectionTestUtils.setField(eventoResource, "eventoRepository", eventoRepository);
        this.restEventoMockMvc = MockMvcBuilders.standaloneSetup(eventoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        evento = new Evento();
        evento.setLocalidad(DEFAULT_LOCALIDAD);
        evento.setHora(DEFAULT_HORA);
        evento.setInicioLatitud(DEFAULT_INICIO_LATITUD);
        evento.setInicioLongitud(DEFAULT_INICIO_LONGITUD);
        evento.setFinal_trayectoLatitud(DEFAULT_FINAL_TRAYECTO_LATITUD);
        evento.setFinal_trayectoLongitud(DEFAULT_FINAL_TRAYECTO_LONGITUD);
    }

    @Test
    @Transactional
    public void createEvento() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento

        restEventoMockMvc.perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(evento)))
                .andExpect(status().isCreated());

        // Validate the Evento in the database
        List<Evento> eventos = eventoRepository.findAll();
        assertThat(eventos).hasSize(databaseSizeBeforeCreate + 1);
        Evento testEvento = eventos.get(eventos.size() - 1);
        assertThat(testEvento.getLocalidad()).isEqualTo(DEFAULT_LOCALIDAD);
        assertThat(testEvento.getHora()).isEqualTo(DEFAULT_HORA);
        assertThat(testEvento.getInicioLatitud()).isEqualTo(DEFAULT_INICIO_LATITUD);
        assertThat(testEvento.getInicioLongitud()).isEqualTo(DEFAULT_INICIO_LONGITUD);
        assertThat(testEvento.getFinal_trayectoLatitud()).isEqualTo(DEFAULT_FINAL_TRAYECTO_LATITUD);
        assertThat(testEvento.getFinal_trayectoLongitud()).isEqualTo(DEFAULT_FINAL_TRAYECTO_LONGITUD);
    }

    @Test
    @Transactional
    public void getAllEventos() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get all the eventos
        restEventoMockMvc.perform(get("/api/eventos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(evento.getId().intValue())))
                .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD.toString())))
                .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA_STR)))
                .andExpect(jsonPath("$.[*].inicioLatitud").value(hasItem(DEFAULT_INICIO_LATITUD.doubleValue())))
                .andExpect(jsonPath("$.[*].inicioLongitud").value(hasItem(DEFAULT_INICIO_LONGITUD.doubleValue())))
                .andExpect(jsonPath("$.[*].final_trayectoLatitud").value(hasItem(DEFAULT_FINAL_TRAYECTO_LATITUD.doubleValue())))
                .andExpect(jsonPath("$.[*].final_trayectoLongitud").value(hasItem(DEFAULT_FINAL_TRAYECTO_LONGITUD.doubleValue())));
    }

    @Test
    @Transactional
    public void getEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", evento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(evento.getId().intValue()))
            .andExpect(jsonPath("$.localidad").value(DEFAULT_LOCALIDAD.toString()))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA_STR))
            .andExpect(jsonPath("$.inicioLatitud").value(DEFAULT_INICIO_LATITUD.doubleValue()))
            .andExpect(jsonPath("$.inicioLongitud").value(DEFAULT_INICIO_LONGITUD.doubleValue()))
            .andExpect(jsonPath("$.final_trayectoLatitud").value(DEFAULT_FINAL_TRAYECTO_LATITUD.doubleValue()))
            .andExpect(jsonPath("$.final_trayectoLongitud").value(DEFAULT_FINAL_TRAYECTO_LONGITUD.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEvento() throws Exception {
        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);
        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Update the evento
        Evento updatedEvento = new Evento();
        updatedEvento.setId(evento.getId());
        updatedEvento.setLocalidad(UPDATED_LOCALIDAD);
        updatedEvento.setHora(UPDATED_HORA);
        updatedEvento.setInicioLatitud(UPDATED_INICIO_LATITUD);
        updatedEvento.setInicioLongitud(UPDATED_INICIO_LONGITUD);
        updatedEvento.setFinal_trayectoLatitud(UPDATED_FINAL_TRAYECTO_LATITUD);
        updatedEvento.setFinal_trayectoLongitud(UPDATED_FINAL_TRAYECTO_LONGITUD);

        restEventoMockMvc.perform(put("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedEvento)))
                .andExpect(status().isOk());

        // Validate the Evento in the database
        List<Evento> eventos = eventoRepository.findAll();
        assertThat(eventos).hasSize(databaseSizeBeforeUpdate);
        Evento testEvento = eventos.get(eventos.size() - 1);
        assertThat(testEvento.getLocalidad()).isEqualTo(UPDATED_LOCALIDAD);
        assertThat(testEvento.getHora()).isEqualTo(UPDATED_HORA);
        assertThat(testEvento.getInicioLatitud()).isEqualTo(UPDATED_INICIO_LATITUD);
        assertThat(testEvento.getInicioLongitud()).isEqualTo(UPDATED_INICIO_LONGITUD);
        assertThat(testEvento.getFinal_trayectoLatitud()).isEqualTo(UPDATED_FINAL_TRAYECTO_LATITUD);
        assertThat(testEvento.getFinal_trayectoLongitud()).isEqualTo(UPDATED_FINAL_TRAYECTO_LONGITUD);
    }

    @Test
    @Transactional
    public void deleteEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);
        int databaseSizeBeforeDelete = eventoRepository.findAll().size();

        // Get the evento
        restEventoMockMvc.perform(delete("/api/eventos/{id}", evento.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Evento> eventos = eventoRepository.findAll();
        assertThat(eventos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
