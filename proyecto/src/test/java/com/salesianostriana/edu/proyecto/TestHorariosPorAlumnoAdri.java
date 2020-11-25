package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.*;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.ExcepcionServicio;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestHorariosPorAlumnoAdri {

    @Mock
    HorarioRepository hoRepositorio;

    @Mock
    ExcepcionServicio exServ;

    @InjectMocks
    HorarioServicio hoServicio;

    /*
    *
    * Vamos a comprobar cual es el horario de un alumno por defecto
    * sin ningún tipo de dato elaborado y con un curso que tenga una lista
    * de asignatura vacia, además de una lista de ampliaciones de asignaturas que se
    * encuentra vacía
    *
    * */
    @Test
    @DisplayName("Horario curso sin asignaturas ampliaciones vacias")
    public void horarioCursoAlumnoSinAsignaturasListaAmpliacionesVaciaListaVacia(){

        Alumno a1 = new Alumno();
        a1.setCurso(new Curso());
        List<Ampliacion> l1 = List.of();

        assertEquals(l1, hoServicio.horariosPorAlumno(a1, l1));
    }

    /*
     *
     * Vamos a comprobar cual es el horario de un alumno con un curso que contenga
     * la misma asignatura que contenga una ampliación de otra asignatura y en estado aceptado que se espera
     * que se añada su horario a una lista junto al horario de la asignatura que contiene el curso
     * del alumno.
     *
     * */

    @Test
    @DisplayName("Horario curso con asignaturas ampliaciones llena")
    public void horarioCursoAlumnoAsignaturasListaAmpliacionesVaciaListaLLena(){
        Alumno a1 = new Alumno();
        Curso c1 = new Curso();

        Asignatura as1 = new Asignatura("Física", c1, true);
        Asignatura as2 = new Asignatura("Mates" , new Curso(), true);

        Horario h1 = new Horario(2, 1, as1,  true);
        Horario h2 = new Horario(4, 1, new Asignatura(), true);

        as1.setHorarios(List.of(
                h1
        ));

        as2.setHorarios(List.of(
                h2
        ));

        c1.getAsignaturas().add(as1);

        a1.setCurso(c1);

        List<Ampliacion> l1 = List.of(
                new Ampliacion(new AmpliacionPK(), a1, as2, LocalDate.now(), LocalDate.now(), "Aceptado")
        );

        assertEquals(List.of(h1, h2), hoServicio.horariosPorAlumno(a1, l1));
    }

    /*
     *
     * Vamos a comprobar cual es el horario de un alumno con un curso que contenga
     * la misma asignatura y que no contenga ninguna ampliación de asignatura. Se espera
     * que se añada su horario a una lista
     *
     * */

    @Test
    @DisplayName("Horario curso con asignaturas ampliaciones vacias")
    public void horarioCursoAlumnoAsignaturasListaAmpliacionesVacia(){
        Alumno a1 = new Alumno();
        Curso c1 = new Curso();

        Asignatura as1 = new Asignatura("Física", c1, true);

        Horario h1 = new Horario(2, 1, as1,  true);

        as1.setHorarios(List.of(
                h1
        ));

        c1.getAsignaturas().add(as1);

        a1.setCurso(c1);

        List<Ampliacion> l1 = List.of(
        );

        assertEquals(List.of(h1), hoServicio.horariosPorAlumno(a1, l1));
    }

    /*
     *
     * Vamos a comprobar cual es el horario de un alumno con un curso que contenga
     * la misma asignatura que contenga una ampliación de otra asignatura y en estado diferente a aceptado.
     * Se espera que se añada a la lista solo el horario de la asignatura del curso.
     *
     * */

    @Test
    @DisplayName("Horario curso con asignaturas ampliaciones llena con estado diferente a aceptado")
    public void horarioCursoAlumnoAsignaturasListaAmpliacionesLLenaNoAprobado(){
        Alumno a1 = new Alumno();
        Curso c1 = new Curso();

        Asignatura as1 = new Asignatura("Física", c1, true);
        Asignatura as2 = new Asignatura("Mates" , new Curso(), true);

        Horario h1 = new Horario(2, 1, as1,  true);
        Horario h2 = new Horario(4, 1, new Asignatura(), true);

        as1.setHorarios(List.of(
                h1
        ));

        as2.setHorarios(List.of(
                h2
        ));

        c1.getAsignaturas().add(as1);

        a1.setCurso(c1);

        List<Ampliacion> l1 = List.of(
                new Ampliacion(new AmpliacionPK(), a1, as2, LocalDate.now(), LocalDate.now(), "Diferente de aceptado")
        );

        assertEquals(List.of(h1), hoServicio.horariosPorAlumno(a1, l1));
    }

    /*
     *
     * Vamos a comprobar cual es el horario de un alumno con un curso que contenga
     * la misma asignatura que contenga una ampliación de otra asignatura y en estado aceptado.
     * Se espera que se añada a la lista solo el horario de la ampliacion del curso.
     *
     * */

    @Test
    @DisplayName("Horario curso con asignaturas ampliaciones llena expeciones misma asignatura")
    public void horarioCursoAlumnoAsignaturasListaAmpliacionesLLenaExcepcionesPrevistas(){
        Alumno a1 = new Alumno();
        Curso c1 = new Curso();

        Asignatura as1 = new Asignatura("Física", c1, true);
        Asignatura as2 = new Asignatura("Mates" , new Curso(), true);

        Horario h1 = new Horario(2, 1, as1,  true);
        Horario h2 = new Horario(4, 1, new Asignatura(), true);

        as1.setHorarios(List.of(
                h1
        ));

        as2.setHorarios(List.of(
                h2
        ));

        c1.getAsignaturas().add(as1);

        a1.setCurso(c1);

        List<Ampliacion> l1 = List.of(
                new Ampliacion(new AmpliacionPK(), a1, as2, LocalDate.now(), LocalDate.now(), "Aceptado")
        );

        Mockito.when(exServ.buscarExistenciaTerminadaExcepcion(as1, a1)).thenReturn(Optional.of(new Excepcion()));

        assertEquals(List.of(h2), hoServicio.horariosPorAlumno(a1, l1));
    }

    /*
     *
     * Vamos a comprobar cual es el horario de un alumno con un curso que contenga
     * la misma asignatura que no contenga ninguna ampliación.
     * Se espera que no se añada nada.
     *
     * */

    @Test
    @DisplayName("Horario curso con asignaturas ampliaciones vacias expeciones misma asignatura")
    public void horarioCursoAlumnoAsignaturasListaAmpliacionesVaciaExcepcionesPrevistas(){
        Alumno a1 = new Alumno();
        Curso c1 = new Curso();

        Asignatura as1 = new Asignatura("Física", c1, true);
        Asignatura as2 = new Asignatura("Mates" , new Curso(), true);

        Horario h1 = new Horario(2, 1, as1,  true);
        Horario h2 = new Horario(4, 1, new Asignatura(), true);

        as1.setHorarios(List.of(
                h1
        ));

        as2.setHorarios(List.of(
                h2
        ));

        c1.getAsignaturas().add(as1);

        a1.setCurso(c1);

        List<Ampliacion> l1 = List.of();

        Mockito.when(exServ.buscarExistenciaTerminadaExcepcion(as1, a1)).thenReturn(Optional.of(new Excepcion()));

        assertEquals(List.of(), hoServicio.horariosPorAlumno(a1, l1));
    }





}
