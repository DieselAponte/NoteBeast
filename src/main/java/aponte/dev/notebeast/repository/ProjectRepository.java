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
    Project delete(Project entity);

    //Metodos Adicionales
    Project readByTitle(String title);
}
