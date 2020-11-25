package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Alumno;
import com.salesianostriana.edu.proyecto.modelo.Ampliacion;
import com.salesianostriana.edu.proyecto.repositorio.ExcepcionRepository;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.ExcepcionServicio;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHorariosPorAlumnoAdri {

    @Mock
    HorarioRepository hoRepositorio;

    @InjectMocks
    HorarioServicio hoServicio;

    @Test
    public void horarioAlumnoNuloListaVacia(){
        Alumno a1 = null;
        List<Ampliacion> l1 = List.of();

        assertEquals(List.of(), l1);
    }

    @Test
    public void horarioAlumnoNuloListaLlena(){
        Alumno a1 = null;
        List<Ampliacion> l1 = List.of();

        assertEquals(List.of(), l1);
    }



}
