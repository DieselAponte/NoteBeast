package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Task;
import aponte.dev.notebeast.util.Priority;
import aponte.dev.notebeast.util.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends Repository<Task, Integer> {
    //CRUD Operations
    @Override
    Task create(Task task);
    @Override
    Task update(Task task);
    @Override
    void delete(Task task);
    @Override
    List<Task> readAll();
    @Override
    Optional<Task> readById(Integer taskId);

    //Metodos adicionales
    List<Task> readByPriority(Priority priority);
    Optional<Task> readByTitle(String title);
    List<Task> readByStatus(TaskStatus status);
}
