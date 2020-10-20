package com.salesianostriana.edu.proyecto.servicio;

import com.salesianostriana.edu.proyecto.modelo.Asignatura;
import com.salesianostriana.edu.proyecto.modelo.Curso;
import com.salesianostriana.edu.proyecto.modelo.Horario;
import com.salesianostriana.edu.proyecto.repositorio.HorarioRepository;
import com.salesianostriana.edu.proyecto.servicio.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HorarioServicio extends BaseService<Horario, Long, HorarioRepository> {

    private final AsignaturaServicio asignaturaServicio;
    private final CursoServicio cursoServicio;

    public HorarioServicio(HorarioRepository repo, AsignaturaServicio asignaturaServicio, CursoServicio cursoServicio) {
        super(repo);
        this.asignaturaServicio = asignaturaServicio;
        this.cursoServicio = cursoServicio;
    }

    public void cargarListado() {
        List<Horario> result = new ArrayList<>();

        String path = "classpath:Horario.csv";
        try {
            // @formatter:off
            result = Files.lines(Paths.get(ResourceUtils.getFile(path).toURI())).skip(1).map(line -> {
                String[] values = line.split(";");
                return new Horario(Integer.parseInt(values[2]), Integer.parseInt(values[3]),
                        asignaturaServicio.findByNameCurs(values[0],values[1]), true);

            }).collect(Collectors.toList());
            // @formatter:on

        } catch (Exception e) {
            System.err.println("Error de lectura del fichero de datos de horarios.");
            System.exit(-1);
        }

        for(Horario h : result){
           // h.getAsigntura().addHorario(h);
            this.save(h);
           // asignaturaServicio.edit(h.getAsigntura());
        }

    }

    public List<Horario> findByCurso (Curso curso){
        String nombre = curso.getNombre();
        List<Horario> lista = new ArrayList<>();

        for(Asignatura asig : asignaturaServicio.findByCurs(nombre)){
            for(Horario h : asig.getHorarios()){
                lista.add(h);
            }
        }
        return lista;
    }

    public List<List<Horario>> ordenarFinal (List<Horario> lista){

        List<List<Horario>> listaF = new ArrayList<>();
        for(int i=1;i<7;i++){
            listaF.add(this.ordenar(this.listaDia(lista, i)));
        }

        return listaF;
    }

    public List<Horario> ordenar (List<Horario> lista){
        lista = lista.stream()
                .sorted(Comparator.comparingInt(Horario::getDia))
                .collect(Collectors.toList());
        return lista;
    }

    public List<Horario> listaDia (List<Horario> lista, int dia){

        List<Horario> listaF = new ArrayList<>();
            for(Horario h : lista){
                if(h.getTramo()==dia){
                    listaF.add(h);
                }
        }
        return listaF;
    }



}
