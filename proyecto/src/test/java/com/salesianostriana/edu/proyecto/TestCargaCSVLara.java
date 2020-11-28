package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.modelo.Titulo;
import com.salesianostriana.edu.proyecto.repositorio.AsignaturaRepository;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.AsignaturaServicio;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCargaCSVLara {
    @Mock
    public AsignaturaRepository repositorio;

    @InjectMocks
    public AsignaturaServicio asignaturaServicio= new AsignaturaServicio(repositorio,null,null);

    HorarioServicio servicio = new HorarioServicio(null, asignaturaServicio, null, null);

    static Titulo titulo;
    static Curso curso;
    static Asignatura asig1;
    static Horario h1;

    @BeforeAll
    static void cargar() {
        titulo = new Titulo("DAM", true);
        curso = new Curso("primero", 1, titulo, true);
        asig1 = new Asignatura("dato", curso, true);
        curso.addAsignatura(asig1);
        titulo.addCurso(curso);


    }

    @Test
    @DisplayName("CSV con 1 dato")
    public void CSV1(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            servicio.cargarListadoTest("src/main/resources/Horario1");
        });
    }

    @Test
    @DisplayName("CSV con 2 datos")
    public void CSV2(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            servicio.cargarListadoTest("src/main/resources/Horario2");
        });
    }
    @Test
    @DisplayName("CSV con 3 datos")
    public void CSV3(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            servicio.cargarListadoTest("src/main/resources/Horario3");
        });
    }

    @Test
    @DisplayName("CSV con 4 datos")
    public void CSV4(){
        h1=new Horario(1,1,asig1,true);
        List<Asignatura> listaAsig =new ArrayList<Asignatura>();
        listaAsig.add(asig1);


        Mockito.when(asignaturaServicio.findByNameCurs("dato","primero")).thenReturn(asig1);
        Mockito.when(asignaturaServicio.findAll()).thenReturn(listaAsig);

        List<Horario> lista=servicio.cargarListadoTest("src/main/resources/Horario4");
        Assertions.assertEquals(h1, lista.get(0));
    }

   /* @Test
    @DisplayName("CSV con 5 datos")
    public void CSV5(){
        //assertThrows(IndexOutOfBoundsException.class, () -> {
            servicio.cargarListadoTest("src/main/resources/Horario5");
        //});
    }*/
}
