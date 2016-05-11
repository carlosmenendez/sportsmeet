package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "hora")
    private ZonedDateTime hora;

    @Column(name = "inicio_latitud")
    private Double inicioLatitud;

    @Column(name = "inicio_longitud")
    private Double inicioLongitud;

    @Column(name = "final_trayecto_latitud")
    private Double final_trayectoLatitud;

    @Column(name = "final_trayecto_longitud")
    private Double final_trayectoLongitud;

    @ManyToOne
    private Deporte deporte;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public ZonedDateTime getHora() {
        return hora;
    }

    public void setHora(ZonedDateTime hora) {
        this.hora = hora;
    }

    public Double getInicioLatitud() {
        return inicioLatitud;
    }

    public void setInicioLatitud(Double inicioLatitud) {
        this.inicioLatitud = inicioLatitud;
    }

    public Double getInicioLongitud() {
        return inicioLongitud;
    }

    public void setInicioLongitud(Double inicioLongitud) {
        this.inicioLongitud = inicioLongitud;
    }

    public Double getFinal_trayectoLatitud() {
        return final_trayectoLatitud;
    }

    public void setFinal_trayectoLatitud(Double final_trayectoLatitud) {
        this.final_trayectoLatitud = final_trayectoLatitud;
    }

    public Double getFinal_trayectoLongitud() {
        return final_trayectoLongitud;
    }

    public void setFinal_trayectoLongitud(Double final_trayectoLongitud) {
        this.final_trayectoLongitud = final_trayectoLongitud;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Evento evento = (Evento) o;
        if(evento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + id +
            ", localidad='" + localidad + "'" +
            ", hora='" + hora + "'" +
            ", inicioLatitud='" + inicioLatitud + "'" +
            ", inicioLongitud='" + inicioLongitud + "'" +
            ", final_trayectoLatitud='" + final_trayectoLatitud + "'" +
            ", final_trayectoLongitud='" + final_trayectoLongitud + "'" +
            '}';
    }
}
