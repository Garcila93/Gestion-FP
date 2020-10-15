package com.salesianostriana.edu.proyecto.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Curso> listaCursos = new ArrayList<>();

    public Titulo(String nombre) {
        this.nombre = nombre;
    }

    public void addCurso(Curso curso) {
        this.listaCursos.add(curso);
        curso.setTitulo(this);
    }

    public void deleteCurso(Curso curso) {
        this.listaCursos.remove(curso);
        curso.setTitulo(null);
    }

}
