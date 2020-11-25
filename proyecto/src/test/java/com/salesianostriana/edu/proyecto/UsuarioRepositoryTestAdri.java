package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Alumno;
import com.salesianostriana.edu.proyecto.modelo.Usuario;
import com.salesianostriana.edu.proyecto.repositorio.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTestAdri {

    @Autowired
    UsuarioRepository usuarioRepository;

    static Usuario a1;

    @BeforeAll
    public static void inicializarUsuario(){
        a1 = new Alumno("prueba", "prueba", "prueba", null);
    }


    @Test
    public void probarGuardaAlumno(){
        Usuario u1 = new Alumno("email", "nombre", "apellidos", null);
        usuarioRepository.save(u1);
        assertNotEquals(Optional.empty() ,usuarioRepository.findFirstByEmail("email"));
    }

    @Test
    public void eliminarAlumno(){
        usuarioRepository.save(a1);
        usuarioRepository.delete(a1);
        assertEquals(Optional.empty() ,usuarioRepository.findFirstByEmail("prueba"));
    }

    @Test
    public void encontrarTodosLosAlumnos(){
        usuarioRepository.save(a1);
        assertEquals(List.of(a1) ,usuarioRepository.findAll());
    }

    @Test
    public void encontrarAlumnoPorEmail(){
        usuarioRepository.save(a1);
        assertEquals(a1 ,usuarioRepository.findFirstByEmail("prueba"));
    }

    @Test
    public void encontrarAlumnoPorId(){
        a1.setId(Long.parseLong("1"));
        usuarioRepository.save(a1);
        assertEquals(Optional.of(a1)  , usuarioRepository.findById(a1.getId()));
    }

}
