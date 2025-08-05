package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    void delete(Note entity);

    //Metodos adicionales
    Optional<Note> readByTitle(String title);
    List<Note> readRecentNotes(); //Cuidado con las nullException
    void insertAssociatedResources(int projectId, Note note);

    //Metodos privados
    //Note buildFromResultSet(ResultSet rs) throws SQLException;

}
