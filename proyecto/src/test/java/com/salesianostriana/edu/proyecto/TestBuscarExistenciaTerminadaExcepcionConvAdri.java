package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.*;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TestBuscarExistenciaTerminadaExcepcionConvAdri {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired private ExcepcionRepository repository;

    @Test
    @DisplayName("Comprobar obtención de una individual aislada")
    void comprobarLaObtencionIndividualAislada(){
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
        entityManager.persist(excepc);
        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig1, a1).orElse(null));
        Assertions.assertEquals(excepc, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar sobre una lista vacia")
    void comprobarSobreUnaListaVacia(){
        List<Excepcion> lista2 = new ArrayList();
        Titulo tit = new Titulo("titulo",true);
        Curso curso = new Curso("curso",1,tit, true);
        Asignatura asig1 = new Asignatura("asignatura",curso,true);
        Alumno a1 = new Alumno("","","",curso);
        curso.addAsignatura(asig1);
        entityManager.persist(tit);
        entityManager.persist(curso);
        entityManager.persist(a1);
        entityManager.persist(asig1);
        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig1, a1).orElse(null));
        Assertions.assertEquals(null, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar obtención de una individual no aislada")
    void comprobarObtencionIndividualNoAislada(){
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
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
        excepc2.setTipo("Convalidación");
        entityManager.persist(excepc2);
        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig1, a1).orElse(null));
        Assertions.assertEquals(excepc, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar en multiples que no se encuentra la buscada por asignatura")
    void comprobarMultiplesNoEncontradasBuscadaPorAsignatura(){
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
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
        excepc2.setTipo("Convalidación");
        entityManager.persist(excepc2);

        Asignatura asig3 = new Asignatura("asignatura3",curso,true);
        curso.addAsignatura(asig3);
        entityManager.persist(asig3);

        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig3, a1).orElse(null));
        Assertions.assertEquals(null, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar en multiples que no se encuentra la buscada por tipo")
    void comprobarMultiplesNoEncontradasEnBusquedaTipo(){
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
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
        excepc2.setTipo("Convalidación");
        entityManager.persist(excepc2);

        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig2, a1).orElse(null));
        Assertions.assertEquals(excepc2, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar en multiples que no se encuentra la buscada por estado")
    void comprobarMultiplesNoEncontradasPorEstado(){
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
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
        excepc2.setTipo("Convalidación");
        entityManager.persist(excepc2);

        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig2, a1).orElse(null));
        Assertions.assertEquals(null, lista2.get(0));
    }

    @Test
    @DisplayName("Comprobar en multiples que varias son las buscadas")
    void comprobarMultiplesVariasBuscadas(){
        List<Excepcion> lista1 = new ArrayList();
        List<Excepcion> lista2 = new ArrayList();
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
        excepc.setTipo("Convalidación");
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
        excepc2.setTipo("Convalidación");
        entityManager.persist(excepc2);

        Asignatura asig3 = new Asignatura("asignatura3",curso,true);
        curso.addAsignatura(asig3);
        entityManager.persist(asig3);
        Excepcion excepc3 = new Excepcion();
        ExcepcionPK pk3 = new ExcepcionPK(a1.getId(), asig2.getId());
        excepc3.setAlumno(a1);
        excepc3.setId(pk3);
        excepc3.setAsignatura(asig3);
        excepc3.setEstado("Aceptado");
        excepc3.setTipo("Convalidación");
        entityManager.persist(excepc3);

        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig3, a1).orElse(null));
        lista2.add(repository.buscarExistenciaTerminadaExcepcionConv(asig2, a1).orElse(null));

        lista1.add(excepc3);
        lista1.add(excepc2);

        Assertions.assertEquals(true, lista1.containsAll(lista2));
    }
}
