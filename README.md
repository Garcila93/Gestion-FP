# Proyecto de gestión de FP - Testeo

Lista de métodos y consultas testeados:  

| Autor inicial | Clase \- línea             | Firma del método                                                                                     |
|---------------|----------------------------|------------------------------------------------------------------------------------------------------|
| Lara          | ExcepcionRepository \- 16  | public Optional<Excepcion> buscarExistenciaTerminadaExcepcion\( Asignatura Asig, Alumno alumno\)     |
| Jorge         |  ExcepcionRepository \- 20 | public Optional<Excepcion> buscarExistenciaTerminadaExcepcionExc\(Asignatura Asig, Alumno alumno\)   |
| Adrián        |  ExcepcionRepository \- 23 | public Optional<Excepcion> buscarExistenciaTerminadaExcepcionConv\(Asignatura Asig,  Alumno alumno\) |
| Jorge         |  AsignaturaServicio \-106  | public List<Asignatura> asignaturasPorAlumno\(Alumno alumno\)                                        |
| Lara          |  HorarioServicio \- 295    | public List<Horario> cargarListadoTest\(String nombre\)                                              |
| Adrián        |  HorarioSerivicio \- 134   | public List<Horario> horariosPorAlumno\(Alumno alumno, List<Ampliacion> listaAmpliaciones\)          |
| Adrián        |  UsuarioRepository         | UsuarioRepository en su totalidad                                                                    |
| Jorge         |  HorarioSerivicio \- 196   | public List<Horario> ordenar \(List<Horario> lista\)                                                 |
| Juan          | HorarioSercivio \- 99      | public boolean solapaHora\(Horario horario\)                                                         |
| Juan          | HorarioServicio \- 237     | public List<Horario> listaTramo\(List<Horario> lista, int dia\)                                      |
