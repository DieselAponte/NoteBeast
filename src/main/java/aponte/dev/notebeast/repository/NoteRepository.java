package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends Repository<Note, Integer> {
    @Override
    List<Note> readAll();
    @Override
    Optional<Note> readById(Integer id);
    @Override
    Note create(Note entity);
    @Override
    Note update(Note entity);
    @Override
    Note delete(Note entity);

    //Metodos adicionales
    Note readByAffiliatedProject(Integer projectId);
    Note readByTitle(String title);
    //Note readByFavorite(boolean favorite);
}
