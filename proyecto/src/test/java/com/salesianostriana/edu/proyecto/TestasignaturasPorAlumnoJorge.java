package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Alumno;
import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.AsignaturaRepository;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import com.salesianostriana.edu.proyecto.servicio.AsignaturaServicio;
import com.salesianostriana.edu.proyecto.servicio.ExcepcionServicio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestasignaturasPorAlumnoJorge {

    @Mock
    ExcepcionRepository repositorio;

    @InjectMocks
    ExcepcionServicio servicio;


    static Titulo titulo = new Titulo("Titulo", true);
    static Curso curso = new Curso("curso",2,titulo,true);
    static Asignatura asig1 = new Asignatura("asig1",curso,true);
    static Asignatura asig2 = new Asignatura("asig2",curso,true);
    static Asignatura asig3 = new Asignatura("asig3",curso,true);

    static Alumno a = new Alumno("email","pepe","pepe",curso);

}
