package aponte.dev.notebeast.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T,K> {
    List<T> readAll();
    Optional<T> readById(K criteria);
    T create(T entity);
    T update(T entity);
    T delete(T entity);
}
