package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.*;
import com.salesianostriana.edu.proyecto.repositorio.AsignaturaRepository;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import com.salesianostriana.edu.proyecto.servicio.AsignaturaServicio;
import com.salesianostriana.edu.proyecto.servicio.ExcepcionServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class TestasignaturasPorAlumnoJorge {

    @Mock
    ExcepcionRepository repositorio;

    @InjectMocks
    ExcepcionServicio servicio = new ExcepcionServicio(repositorio);

    AsignaturaServicio asignaturaServicio = new AsignaturaServicio(null, null, servicio);

    static Titulo titulo;
    static Curso curso;
    static Asignatura asig1;
    static Asignatura asig2;
    static Asignatura asig3;
    static Asignatura asig4;
    static Alumno a;
    static Optional<Excepcion> excepc;
    static Optional<Excepcion> excepc2;
    static ExcepcionPK pk;
    static ExcepcionPK pk2;

    @BeforeAll
    static void cargar(){
        titulo = new Titulo("Titulo", true);
        curso = new Curso("curso",2,titulo,true);
        asig1 = new Asignatura("asig1",curso,true);
        asig2 = new Asignatura("asig2",curso,true);
        asig3 = new Asignatura("asig3",curso,true);
        asig4 = new Asignatura("asig4",curso,true);
        curso.setAsignaturas(Set.of(asig1, asig2, asig3, asig4));
        a = new Alumno("email","pepe","pepe",curso);
        a.setId(1L);
        asig1.setId(1L);
        asig2.setId(2L);
        asig3.setId(3L);
        asig4.setId(4L);

    }


    @Test
    @DisplayName("Asignaturas del alumno sin convalidadas ni aprobadas del curso anterior")
    void listaSinExcSinApro(){

        List<Asignatura> esperado = List.of(asig1, asig2, asig3, asig4);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));


    }

    @Test
    @DisplayName("Asignaturas del alumno con convalidadas y sin aprobadas del curso anterior")
    void listaExcSinApro(){



        excepc = Optional.of(new Excepcion());
        pk = new ExcepcionPK(a.getId(), asig1.getId());
        excepc.get().setAlumno(a);
        excepc.get().setAsignatura(asig1);
        excepc.get().setId(pk);
        excepc.get().setEstado("Aceptado");

        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig1,a)).thenReturn(excepc);

        List<Asignatura> esperado = List.of(asig2, asig3, asig4);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));

    }

    @Test
    @DisplayName("Asignaturas del alumno sin convalidadas y con aprobadas del curso anterior")
    void listaSinExcApro(){
        a.setAsignaturas(Set.of(asig4));

        List<Asignatura> esperado = List.of(asig2, asig3, asig1);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));
    }

    @Test
    @DisplayName("Asignaturas del alumno con convalidadas y aprobadas del curso anterior")
    void listaExcApro(){
        a.setAsignaturas(Set.of(asig4));

        excepc = Optional.of(new Excepcion());
        pk = new ExcepcionPK(a.getId(), asig1.getId());
        excepc.get().setAlumno(a);
        excepc.get().setId(pk);
        excepc.get().setAsignatura(asig1);
        excepc.get().setEstado("Aceptado");

        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig1,a)).thenReturn(excepc);

        List<Asignatura> esperado = List.of(asig2, asig3);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));

    }

    @Test
    @DisplayName("Asignaturas del alumno con varias convalidadas")
    void listaVarExc(){
        excepc2 = Optional.of(new Excepcion());
        pk2 = new ExcepcionPK(a.getId(), asig4.getId());
        excepc2.get().setAlumno(a);
        excepc2.get().setId(pk2);
        excepc2.get().setAsignatura(asig4);
        excepc2.get().setEstado("Aceptado");

        excepc = Optional.of(new Excepcion());
        pk = new ExcepcionPK(a.getId(), asig1.getId());
        excepc.get().setAlumno(a);
        excepc.get().setId(pk);
        excepc.get().setAsignatura(asig1);
        excepc.get().setEstado("Aceptado");

        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig4,a)).thenReturn(excepc2);
        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig1,a)).thenReturn(excepc);

        List<Asignatura> esperado = List.of(asig2, asig3);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));

    }

    @Test
    @DisplayName("Asignaturas del alumno con varias convalidadas y alguna aprobada del curso anterior")
    void listaVarExcApro(){

        a.setAsignaturas(Set.of(asig3));

        excepc2 = Optional.of(new Excepcion());
        pk2 = new ExcepcionPK(a.getId(), asig4.getId());
        excepc2.get().setAlumno(a);
        excepc2.get().setId(pk2);
        excepc2.get().setAsignatura(asig4);
        excepc2.get().setEstado("Aceptado");

        excepc = Optional.of(new Excepcion());
        pk = new ExcepcionPK(a.getId(), asig1.getId());
        excepc.get().setAlumno(a);
        excepc.get().setId(pk);
        excepc.get().setAsignatura(asig1);
        excepc.get().setEstado("Aceptado");

        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig4,a)).thenReturn(excepc2);
        Mockito.when(servicio.buscarExistenciaTerminadaExcepcion(asig1,a)).thenReturn(excepc);

        List<Asignatura> esperado = List.of(asig2);
        Assertions.assertEquals(true, esperado.containsAll(asignaturaServicio.asignaturasPorAlumno(a)));

    }

}
