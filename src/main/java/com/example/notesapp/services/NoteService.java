package com.example.notesapp.services;

import com.example.notesapp.entities.Note;
import com.example.notesapp.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/*
    Сервис обработки заметок;
*/
@Service
public class NoteService {

    //Создаем ссылку на репозиторий
    private final NoteRepository noteRepository;

    //Внедряем ссылку на репозиторий через конструктор
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //Метод для получения списка всех заметок из БД
    public List<Note> allNotes(){

        return noteRepository.findAll();
    }

    //Метод для добавления 1 заметки в БД
    public void add (Note note){
        noteRepository.save(note);
    }

    //Метод для удаления заметКи по id
    public void delete (Long id){
        noteRepository.deleteById(id);
    }

    //Метод для редактирования заметки
    public void edit(Note note){
        Optional<Note> row=noteRepository.findById(note.getId());

        if (row.isPresent()){
            Note updateNote=row.get();
            updateNote.setTitle(note.getTitle());
            updateNote.setText(note.getText());

            noteRepository.save(updateNote);
        }

    }

    //Метод для поиска заметок, в которых содержится указанная подстрока.
    public List<Note> search(String searchStr){

        //Получаем лист заметок из БД
        List<Note> noteList= noteRepository.findAll();

        //Создаем лист, в который кладем заметки, в который найдены совпадения
        LinkedList<Note> resultNoteList=new LinkedList<>();

        //Перебираем лист в поисках совпадений в заметках (в заголовкае или в тексте)
        for (Note note: noteList) {
            if (note.getTitle().contains(searchStr)||note.getText().contains(searchStr))
                resultNoteList.add(note);
        }

      return resultNoteList;
    }

    //Получить нужную заметку
    public Note getNote(Long id){
        return noteRepository.getById(id);
    }

}
