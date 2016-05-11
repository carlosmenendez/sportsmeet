package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Deporte.
 */
@Entity
@Table(name = "deporte")
public class Deporte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "deporte")
    @JsonIgnore
    private Set<Evento> eventos = new HashSet<>();

    @OneToMany(mappedBy = "deporte")
    @JsonIgnore
    private Set<Deportefavorito> deporteFavoritos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    public Set<Deportefavorito> getDeporteFavoritos() {
        return deporteFavoritos;
    }

    public void setDeporteFavoritos(Set<Deportefavorito> deporteFavoritos) {
        this.deporteFavoritos = deporteFavoritos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deporte deporte = (Deporte) o;
        if(deporte.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, deporte.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Deporte{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            '}';
    }
}
