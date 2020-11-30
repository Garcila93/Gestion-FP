package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.AsignaturaRepository;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.repositorio.TituloRepository;
import com.salesianostriana.edu.proyecto.servicio.AsignaturaServicio;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import com.salesianostriana.edu.proyecto.servicio.TituloServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestSolapaHoraJuan {

    @Mock
    public TituloRepository tituloRepository;
    @Mock
    public AsignaturaRepository asignaturaRepository;

    TituloServicio tituloServicio = new TituloServicio(tituloRepository);
    AsignaturaServicio asignaturaServicio = new AsignaturaServicio(asignaturaRepository,null,null);


    @InjectMocks
    HorarioServicio horarioServicio = new HorarioServicio(null,asignaturaServicio,tituloServicio,null);

    static Horario horario;
    static Asignatura asignatura;
    static Curso curso;
    static Titulo titulo;


    @BeforeAll
    static void inicializar(){
        titulo = new Titulo("Titulo",true);
        curso = new Curso("curso",2012,titulo,true);
        asignatura = new Asignatura("asi1",curso,true);
        horario = new Horario(4,6,asignatura,true);
        curso.addAsignatura(asignatura);
        asignatura.addHorario(horario);
        titulo.addCurso(curso);
    }

    @Test
    @DisplayName("Horario no existente en la base de datos")
    void horarioExistente(){

        Horario horario2 = new Horario(4,6,asignatura,true);

        List<Titulo> lista = List.of(titulo);
        List<Asignatura> lista2 = List.of(asignatura);
        Mockito.when(asignaturaServicio.findAll()).thenReturn(lista2);
        Mockito.when(tituloServicio.findAll()).thenReturn(lista);

        boolean resp = horarioServicio.solapaHora(horario2);
        Assertions.assertFalse(resp);
    }




}
