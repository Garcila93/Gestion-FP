package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.*;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

/*
* @Query("Select e From Excepcion e Where e.asignatura= :ASIGNATURA and e.alumno= :AlUMNO and e.estado='Aceptado'")
    public Optional<Excepcion> buscarExistenciaTerminadaExcepcion(@Param("ASIGNATURA") Asignatura Asig, @Param("AlUMNO") Alumno alumno);
*/
@DataJpaTest
public class TestbuscarExistenciaTerminadaExcepcionLara {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExcepcionRepository excepcionRepository;

    @Test
    @DisplayName("Vamos a comprobar que obtenemos un solo caso especifico")
    void comprobarSoloUno(){
        List<Excepcion> lista1= new ArrayList<>();
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig1 = new Asignatura("asignatura",curso,true);
        Alumno a1 = new Alumno("","","",curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(asig1);
        entityManager.persist(a1);
        Excepcion excepc = new Excepcion();
        ExcepcionPK pk = new ExcepcionPK(a1.getId(), asig1.getId());
        excepc.setAlumno(a1);
        excepc.setId(pk);
        excepc.setAsignatura(asig1);
        excepc.setEstado("Aceptado");
        entityManager.persist(excepc);
        lista1.add(excepcionRepository.buscarExistenciaTerminadaExcepcion(asig1,a1).orElse(null));
        Assertions.assertEquals(excepc, lista1.get(0));

    }
    @Test
    @DisplayName("Comprobar sobre una lista vacia")
    void comprobarListaVacia(){
        List<Excepcion> lista1 = new ArrayList();
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig1 = new Asignatura("asignatura",curso,true);
        Alumno a1 = new Alumno("","","",curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(a1);
        entityManager.persist(asig1);
        lista1.add(excepcionRepository.buscarExistenciaTerminadaExcepcion(asig1,a1).orElse(null));
        Assertions.assertEquals(null, lista1.get(0));
    }

    @Test
    @DisplayName("Comprobar que obtenemos solo los aceptados")
    void comprobarIndividualMult() {
        List<Excepcion> lista1 = new ArrayList();
        Titulo tit = new Titulo("titulo", true);
        Curso curso = new Curso("curso", 1, tit, true);
        Asignatura asig1 = new Asignatura("asignatura", curso, true);
        Alumno a1 = new Alumno("", "", "", curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(a1);
        entityManager.persist(asig1);
        Excepcion excepc = new Excepcion();
        ExcepcionPK pk = new ExcepcionPK(a1.getId(), asig1.getId());
        excepc.setAlumno(a1);
        excepc.setId(pk);
        excepc.setAsignatura(asig1);
        excepc.setEstado("Aceptado");
        entityManager.persist(excepc);

        Asignatura asig2 = new Asignatura("asignatura2", curso, true);
        Excepcion excepc2 = new Excepcion();
        ExcepcionPK pk2 = new ExcepcionPK(a1.getId(), asig2.getId());
        curso.addAsignatura(asig2);
        entityManager.persist(asig2);
        excepc2.setAlumno(a1);
        excepc2.setId(pk2);
        excepc2.setAsignatura(asig2);
        excepc2.setEstado("Rechazado");
        entityManager.persist(excepc2);
        lista1.add(excepcionRepository.buscarExistenciaTerminadaExcepcion(asig1, a1).orElse(null));
        Assertions.assertEquals(excepc, lista1.get(0));
    }

    @Test
    @DisplayName("Comprobamos que no se encuentra la buscada por asignatura")
    void comprobarNoValidAsign(){
        List<Excepcion> lista1 = new ArrayList();
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig1 = new Asignatura("asignatura",curso,true);
        Alumno a1 = new Alumno("","","",curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(a1);
        entityManager.persist(asig1);
        Excepcion excepc = new Excepcion();
        ExcepcionPK pk = new ExcepcionPK(a1.getId(), asig1.getId());
        excepc.setAlumno(a1);
        excepc.setId(pk);
        excepc.setAsignatura(asig1);
        excepc.setEstado("Aceptado");
        entityManager.persist(excepc);

        Asignatura asig2 = new Asignatura("asignatura2",curso,true);
        Excepcion excepc2 = new Excepcion();
        ExcepcionPK pk2 = new ExcepcionPK(a1.getId(), asig2.getId());
        curso.addAsignatura(asig2);
        entityManager.persist(asig2);
        excepc2.setAlumno(a1);
        excepc2.setId(pk2);
        excepc2.setAsignatura(asig2);
        excepc2.setEstado("Aceptado");
        entityManager.persist(excepc2);

        Asignatura asig3 = new Asignatura("asignatura3",curso,true);
        curso.addAsignatura(asig3);
        entityManager.persist(asig3);

        lista1.add(excepcionRepository.buscarExistenciaTerminadaExcepcion(asig3, a1).orElse(null));
        Assertions.assertEquals(null, lista1.get(0));
    }

    @Test
    @DisplayName("Comprobamos que no se encuentra la buscada por estado")
    void comprobarVariasNoAcep(){
        List<Excepcion> lista1 = new ArrayList();
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig1 = new Asignatura("asignatura",curso,true);
        Alumno a1 = new Alumno("","","",curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(a1);
        entityManager.persist(asig1);
        Excepcion excepc = new Excepcion();
        ExcepcionPK pk = new ExcepcionPK(a1.getId(), asig1.getId());
        excepc.setAlumno(a1);
        excepc.setId(pk);
        excepc.setAsignatura(asig1);
        excepc.setEstado("Aceptado");
        entityManager.persist(excepc);

        Asignatura asig2 = new Asignatura("asignatura2",curso,true);
        Excepcion excepc2 = new Excepcion();
        ExcepcionPK pk2 = new ExcepcionPK(a1.getId(), asig2.getId());
        curso.addAsignatura(asig2);
        entityManager.persist(asig2);
        excepc2.setAlumno(a1);
        excepc2.setId(pk2);
        excepc2.setAsignatura(asig2);
        excepc2.setEstado("Pendiente");
        entityManager.persist(excepc2);

        lista1.add(excepcionRepository.buscarExistenciaTerminadaExcepcionExc(asig2, a1).orElse(null));
        Assertions.assertEquals(null, lista1.get(0));
    }
}
