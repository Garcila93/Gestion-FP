package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.*;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;

/*
@Query("Select e From Excepcion e Where e.asignatura= :ASIGNATURA and e.alumno= :AlUMNO and e.estado='Aceptado' and e.tipo= :TIPO")
    public Optional<Excepcion> buscarExistenciaTerminadaExcepcionExc
    (@Param("ASIGNATURA") Asignatura Asig, @Param("AlUMNO") Alumno alumno, @Param("TIPO") String tipo);
*/

@DataJpaTest
public class TestbuscarExistenciaTerminadaExcepcionExcJorge {

    static Alumno a1;
    static Alumno a2;
    static Asignatura as1;
    static Asignatura as2;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ExcepcionRepository repository;

    /*
    @BeforeAll
    static void cargarListado(){
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);

    }
    */

    @Test
    void comprobarVacio(){
        boolean esperado = true;
        boolean obtenido = true;
        Alumno a1 = new Alumno();
        Assertions.assertEquals(esperado,obtenido);
    }




}
