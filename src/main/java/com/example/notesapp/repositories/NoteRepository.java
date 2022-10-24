package com.example.notesapp.repositories;

import com.example.notesapp.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Репозиторий для сохранения/получения объектов в/из базы данных.
*/
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
