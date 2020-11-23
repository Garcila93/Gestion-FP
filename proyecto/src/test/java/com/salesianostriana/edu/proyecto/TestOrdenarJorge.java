package com.salesianostriana.edu.proyecto;


import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOrdenarJorge {

    static HorarioServicio horarioServicio = new HorarioServicio(null, null, null, null);
    static  List<Horario> listaComp;
    static  List<Horario> listaParc;
    static  List<Horario> listaParc2;
    static  List<Horario> listaVac;

    @BeforeAll
    static void cargarListado(){
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1,1,asig,true);
        Horario h2 = new Horario(2,1,asig,true);
        Horario h3 = new Horario(3,1,asig,true);
        Horario h4 = new Horario(4,1,asig,true);
        Horario h5 = new Horario(5,1,asig,true);
        listaComp = List.of(h3,h4,h1,h5,h2);
        listaParc = List.of(h3,h4,h1,h5);
        listaParc2 = List.of(h4,h1);
        listaVac = List.of();
    }

    @Test
    @DisplayName("Ordenación de 5 horarios")
    void ordenarListaCompleta() {
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1,1,asig,true);
        Horario h2 = new Horario(2,1,asig,true);
        Horario h3 = new Horario(3,1,asig,true);
        Horario h4 = new Horario(4,1,asig,true);
        Horario h5 = new Horario(5,1,asig,true);
        List<Horario> esperado = List.of(h1,h2,h3,h4,h5);
        Assertions.assertEquals(esperado, horarioServicio.ordenar(listaComp));
    }

    @Test
    @DisplayName("Ordenación de 4 horarios")
    void ordenarListaparcial() {
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1,1,asig,true);
        Horario h2 = new Horario(2);
        Horario h3 = new Horario(3,1,asig,true);
        Horario h4 = new Horario(4,1,asig,true);
        Horario h5 = new Horario(5,1,asig,true);
        List<Horario> esperado = List.of(h1,h2,h3,h4,h5);
        Assertions.assertEquals(esperado, horarioServicio.ordenar(listaParc));
    }

    @Test
    @DisplayName("Ordenación de 2 horarios")
    void ordenarListaparcial2() {
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1,1,asig,true);
        Horario h2 = new Horario(2);
        Horario h3 = new Horario(3);
        Horario h4 = new Horario(4,1,asig,true);
        Horario h5 = new Horario(5);
        List<Horario> esperado = List.of(h1,h2,h3,h4,h5);

        Assertions.assertEquals(esperado, horarioServicio.ordenar(listaParc2));
    }

    @Test
    @DisplayName("Ordenación de ningún horario")
    void ordenarListaVacia() {
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1);
        Horario h2 = new Horario(2);
        Horario h3 = new Horario(3);
        Horario h4 = new Horario(4);
        Horario h5 = new Horario(5);
        List<Horario> esperado = List.of(h1,h2,h3,h4,h5);
        Assertions.assertEquals(esperado, horarioServicio.ordenar(listaVac));
    }

}
