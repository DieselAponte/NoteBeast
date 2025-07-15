package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;
import aponte.dev.notebeast.model.ProgressEntry;

import java.util.List;
import java.util.Optional;

public interface ProgressEntryRepository extends Repository<ProgressEntry, Integer> {
    //CRUD Operations
    @Override
    List<ProgressEntry> readAll();
    @Override
    Optional<ProgressEntry> readById(Integer idProjectAffiliate);
    @Override
    ProgressEntry create(ProgressEntry entity);
    @Override
    ProgressEntry update(ProgressEntry entity);
    @Override
    ProgressEntry delete(ProgressEntry entity);
}
