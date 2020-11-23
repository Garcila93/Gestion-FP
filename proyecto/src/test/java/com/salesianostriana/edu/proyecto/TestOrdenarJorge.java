package com.salesianostriana.edu.proyecto;


import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOrdenarJorge {

    static HorarioServicio horarioServicio;
    static  List<Horario> listaComp;
    static  List<Horario> listaParc;

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
        listaComp = new ArrayList<>();
        listaParc = new ArrayList<>();
        listaComp.add(h3);
        listaComp.add(h4);
        listaComp.add(h1);
        listaComp.add(h5);
        listaComp.add(h2);
        listaParc.add(h3);
        listaParc.add(h4);
        listaParc.add(h1);
        listaParc.add(h5);
    }

    @Test
    @DisplayName("Ordenación de 5 horarios")
    void ordenarListaCompleta() {
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
        Horario h1 = new Horario(1,1,asig,true);
        Horario obtenido = listaComp.get(2);
        Assertions.assertEquals(h1, obtenido);
    }

}
