package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;

import java.util.List;

public interface RecentNotesRepository {
    void registerRecentNote(Note note);
    List<String> readRecentNotes();
}
