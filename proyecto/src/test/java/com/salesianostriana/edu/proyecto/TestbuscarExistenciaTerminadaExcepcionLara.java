package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Alumno;
import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/*
* @Query("Select e From Excepcion e Where e.asignatura= :ASIGNATURA and e.alumno= :AlUMNO and e.estado='Aceptado'")
    public Optional<Excepcion> buscarExistenciaTerminadaExcepcion(@Param("ASIGNATURA") Asignatura Asig, @Param("AlUMNO") Alumno alumno);
*/
@DataJpaTest
public class TestbuscarExistenciaTerminadaExcepcionLara {

    static Alumno a1;
    static Alumno a2;
    static Asignatura as1;
    static Asignatura as2;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ExcepcionRepository excepcionRepository;

    @BeforeAll
    static void cargaListaAlumnos(){
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig = new Asignatura("asignatura",curso,true);
    }

    @Test
    void listaAlumnosVacia(){

    }
}
