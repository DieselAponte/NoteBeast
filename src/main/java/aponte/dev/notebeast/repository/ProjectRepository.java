package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends Repository<Project,Integer>{
    @Override
    List<Project> readAll();
    @Override
    Optional<Project> readById(Integer id);
    @Override
    Project create(Project entity);
    @Override
    Project update(Project entity);
    @Override
    void delete(Project entity);

    //Metodos Adicionales
    Optional<Project> readByTitle(String title);

    /** Metodos privados
     *  void insertObjectives(int projectId, List<String> objectives)
     *  -Añade una lista de objetivos, colocando objetivo por objetivo
     *  haciendo uso de un ciclo 'for' para añadirlos a la tabla de ProjectObjective.
     *
     *  void insertResourcePaths(int projectId, List<String> paths)
     *  -Añade una lista de recursos, colocando documento por documento
     *  haciendo uso de un ciclo 'for' para añadirlos a la tabla ProjectResourcePath.
     *
     *  void insertProgressLog(int projectId, List<ProgressEntry> entries)
     *  -Añade una lista de progresos, colocando progreso por progreso
     *  haciendo uso de un ciclo 'for' para añadirlos a la tabla de ProgressLog
     *
     *  List<String> readObjectives(int projectId)
     *  -Realiza una busqueda por medio del projectId dentro de la tabla ProjectObjective
     *  y todos los objetivos que haya identificado el resultset los ira agregando a una
     *  nueva lista para devolver esa lista con los objetivos recopilados por projectId.
     *
     *  List<String> readPaths(int projectId)
     *  -Realiza una busqueda por medio del projectId dentro de la tabla de ProjectResourcePath
     *  y todos las direcciones de los recursos que haya identificado el resultset los ira
     *  agregando a una nueva lista para devolver esa lista con los objetivos recopilados por projectId.
     *
     *  List<ProgressLog> readProgressLog(int projectId)
     *  -Realiza una busqueda por medio del projectId dentro de la tabla ProgressLog
     *  donde el ResultSet identificara todos los progresos con este valor en especifico.
     *  Dado que los progresos no son un tipo de dato primitivo sino un objeto, antes de agregarlos
     *  a una nueva lista, tiene que reconstruirlos con cada uno de sus parametros y luego agregarlos.
     *  Una vez terminados todos los resultados, y haber sido agregados a la lista, esta es devuelta
     *  por el metodo.
     *
     *  Project buildProjectFromResultSet(ResultSet rs)
     *  -Es un metodo que construye un nuevo objeto Project donde se asignan sus respectivos parametros
     *  que requiere el constructor para instanciarse y se asignan los objetivos, direccion de recursos y
     *  el log de progresos haciendo uso de los metodos set y se devuelve el projecto.
     *
     *  void deleteChildTables(int projectId)
     *  -Es un metodo que almacena en un arreglo de strings los nombres de las otra tablas que almacenan
     *  los atributos del objeto Project como los objetivos, recursos, progresos. En un bluce 'for' se
     *  itera sobre cada una de estas tablas haciendo uso de un query 'DELETE FROM [NombreTable] WHERE project_id = projectId'
     *  este query posteriormente es ejecutado en cada una de las tablas asegurandose de borrar todos los datos de un proyecto.
     */
}
