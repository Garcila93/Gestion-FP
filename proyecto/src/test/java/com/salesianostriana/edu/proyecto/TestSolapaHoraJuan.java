package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.*;
import com.salesianostriana.edu.proyecto.servicio.*;
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

@ExtendWith(MockitoExtension.class)
public class TestSolapaHoraJuan {

    @Mock
    TituloRepository tituloRepository;

    @InjectMocks
    TituloServicio tituloServicio = new TituloServicio(tituloRepository);

    AsignaturaRepository asignaturaRepository;
    HorarioRepository horarioRepository;
    AsignaturaServicio asignaturaServicio = new AsignaturaServicio(asignaturaRepository,null,null);
    HorarioServicio horarioServicio = new HorarioServicio(horarioRepository,asignaturaServicio,tituloServicio,null);

    static Horario horario1;
    static Horario horario2;
    static Asignatura asig1;
    static Asignatura asig2;
    static Asignatura asig3;
    static Curso curso1;
    static Curso curso2;
    static Titulo titulo1;
    static Titulo titulo2;

    @BeforeAll
    static void inicializar(){

        titulo1 = new Titulo("Titulo1",true);
        titulo2 = new Titulo("Titulo2",false);
        curso1 = new Curso("curso1",2012,titulo1,true);
        curso2 = new Curso("curso2",2012,titulo2,false);
        asig1 = new Asignatura("asi1",curso1,true);
        asig2 = new Asignatura("asi2",curso2,false);
        asig3 = new Asignatura("asi3",curso2,true);
        horario1 = new Horario(4,6,asig1,true);
        horario2 = new Horario(2,5,asig2,false);
        curso1.addAsignatura(asig1);
        curso2.addAsignatura(asig2);
        curso2.addAsignatura(asig3);
        asig1.addHorario(horario1);
        asig2.addHorario(horario2);
        asig3.addHorario(horario1);
        titulo1.addCurso(curso1);
        titulo2.addCurso(curso2);
    }
    /*

    En este metodo pasaremos por parametro un horario de alta que contiene una asignatura que tambien esta de alta
    y la asignatura contiene un curso de alta y el curso contiene un titulo de alta

     */

    @Test
    @DisplayName("Horario , curso y titulo de alta no existentes en la base de datos")
    void horarionoExistentedeAlta(){

        Horario horario2 = new Horario(4,6,asig1,true);

        List<Titulo> lista = new ArrayList<>();
        lista.add(titulo1);

        List<Asignatura> lista2 = new ArrayList<>();
        lista2.add(asig1);

        //Mockito.when(tituloServicio.findAll()).thenReturn(lista);
        //Mockito.when(asignaturaServicio.findAll()).thenReturn(lista2);

        boolean resp = horarioServicio.solapaHora(horario2);
        Assertions.assertFalse(resp);
    }

    /*

    En este metodo pasaremos por parametro un horario de baja que contiene una asignatura que tambien esta de baja
    y la asignatura contiene un curso de baja y el curso contiene un titulo de baja

     */

    @Test
    @DisplayName("Horario , curso y titulo de baja no existentes en la base de datos")
    void horarionoExistentedeBaja(){

        Horario horario2 = new Horario(4,6,asig2,false);

        boolean resp = horarioServicio.solapaHora(horario2);
        Assertions.assertFalse(resp);

    }

       /*

    En este metodo pasaremos por parametro un horario de baja que contiene una asignatura que tambien esta de alta
    pero la asignatura contiene un curso de baja y el curso contiene un titulo de baja tambien

     */

    @Test
    @DisplayName("Horario , curso y titulo de baja y alta no existentes en la base de datos")
    void horarionoExistentedeBajayAlta(){

        Horario horario2 = new Horario(4,6,asig3,true);

        boolean resp = horarioServicio.solapaHora(horario2);
        Assertions.assertFalse(resp);

    }
}

