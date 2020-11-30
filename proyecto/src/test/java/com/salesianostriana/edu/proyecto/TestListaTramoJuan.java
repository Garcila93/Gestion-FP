package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestListaTramoJuan {

    static Asignatura asi1;
    static Asignatura asi2;
    static Asignatura asi3;
    static Asignatura asi4;
    static Asignatura asi5;
    static Horario hora1;
    static Horario hora2;
    static Horario hora3;
    static Horario hora4;
    static Horario hora5;

    @Mock
    HorarioRepository horarioRepository;

    @InjectMocks
    HorarioServicio horarioServicio = new HorarioServicio(horarioRepository,null,null,null);


    @BeforeAll
    static void inicializar(){
        //asignatura = new Asignatura("asi1",curso,true);
        //horario = new Horario(4,6,asignatura,true);
        asi1 = new Asignatura("asi1",null,true);
        asi2 = new Asignatura("asi2",null,true);
        asi3 = new Asignatura("asi3",null,true);
        asi4 = new Asignatura("asi3",null,true);
        asi5 = new Asignatura("asi3",null,true);
        hora1 = new Horario(1,3,asi1,true);
        hora2 = new Horario(2,4,asi2,true);
        hora3 = new Horario(3,5,asi3,true);
        hora4 = new Horario(1,5,asi4,false);
        hora5 = new Horario(3,5,asi5,false);
    }

    /*
    En este test comprobaremos que el metodo devuelve la lista correspondiente
    dependiendo del dia que le pasemos por parametro
     */

    @Test
    @DisplayName("Lista de horarios dados de alta")
    void listaHorariosconAlta(){

        List<Horario> lista = new ArrayList<>();
        lista.add(hora1);
        lista.add(hora2);
        lista.add(hora3);
        List<Horario> igual = new ArrayList<>();
        igual.add(hora3);

        List<Horario> resul = horarioServicio.listaTramo(lista,5);
        Assertions.assertIterableEquals(resul,igual);
    }


    /*
    En este test pasaremos como parametro una lista vacia y aunque pasemos
    el dia no devolvera nada
     */

    @Test
    @DisplayName("Lista vacia")
    void listaVacia(){

        List<Horario> lista = new ArrayList<>();
        List<Horario> igual = new ArrayList<>();

        List<Horario> resul = horarioServicio.listaTramo(lista,5);
        Assertions.assertIterableEquals(resul,igual);
    }

    /*
    En este test pasaremos solo horarios de baja
     */

    @Test
    @DisplayName("Lista de horarios dados de baja")
    void listaHorariosdeBaja(){

        List<Horario> lista = new ArrayList<>();
        lista.add(hora1);
        lista.add(hora2);
        lista.add(hora4);
        lista.add(hora5);
        List<Horario> igual = new ArrayList<>();
        igual.add(hora4);
        igual.add(hora5);

        List<Horario> resul = horarioServicio.listaTramo(lista,5);
        Assertions.assertIterableEquals(resul,igual);
    }

    /*
    En este test pasaremos un numero negativo como dia

     */

    @Test
    @DisplayName("Lista de horarios y cadena")
    void listaHorarios(){

        List<Horario> lista = new ArrayList<>();
        lista.add(hora3);
        lista.add(hora2);
        lista.add(hora4);
        List<Horario> igual = new ArrayList<>();

        List<Horario> resul = horarioServicio.listaTramo(lista,-5);
        Assertions.assertIterableEquals(resul,igual);
    }
}
