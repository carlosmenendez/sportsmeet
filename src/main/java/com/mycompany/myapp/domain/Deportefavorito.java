package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.NivelHabilidad;

/**
 * A Deportefavorito.
 */
@Entity
@Table(name = "deporte_favorito")
public class Deportefavorito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dominio")
    private NivelHabilidad nivelDominio;

    @ManyToOne
    private User user;

    @ManyToOne
    private Deporte deporte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NivelHabilidad getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(NivelHabilidad nivelDominio) {
        this.nivelDominio = nivelDominio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deportefavorito deportefavorito = (Deportefavorito) o;
        if(deportefavorito.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, deportefavorito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Deportefavorito{" +
            "id=" + id +
            ", nivelDominio='" + nivelDominio + "'" +
            '}';
    }
}
