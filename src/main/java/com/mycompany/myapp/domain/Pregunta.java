package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Pregunta.
 */
@Entity
@Table(name = "pregunta")
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titulo_question")
    private String titulo_question;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "pregunta")
    @JsonIgnore
    private Set<Respuesta> respuestas = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo_question() {
        return titulo_question;
    }

    public void setTitulo_question(String titulo_question) {
        this.titulo_question = titulo_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Set<Respuesta> respuestas) {
        this.respuestas = respuestas;
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
        Pregunta pregunta = (Pregunta) o;
        if(pregunta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pregunta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pregunta{" +
            "id=" + id +
            ", titulo_question='" + titulo_question + "'" +
            ", question='" + question + "'" +
            '}';
    }
}
