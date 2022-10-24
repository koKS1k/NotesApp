package com.example.notesapp.configurations;

import com.example.notesapp.entities.Note;
import com.example.notesapp.repositories.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedList;
import java.util.List;

/*
    Класс StartConfiguration перед запуском приложения несколько записей в БД.
    После закрытия записи удаляются, т.к. задан параметр create-drop в properties.
*/
@Configuration
public class StartConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(NoteRepository noteRepository){

        List<Note> noteList= new LinkedList<>();

        String text1 = "В своём стремлении улучшить пользовательский опыт мы упускаем" +
                ", что явные признаки победы институционализации являются" +
                " только методом политического участия и заблокированы в рамках своих" +
                " собственных рациональных ограничений.";

        String text2 = "Равным образом рамки и место обучения кадров влечет за собой процесс " +
                "внедрения и модернизации системы обучения кадров, соответствует насущным " +
                "потребностям. Идейные соображения высшего порядка, а также начало " +
                "повседневной работы по формированию позиции позволяет оценить значение модели развития.";

        String text3 = "Товарищи! консультация с широким активом позволяет выполнять важные задания" +
                "по разработке систем массового участия. Таким образом реализация намеченных " +
                "плановых заданий позволяет оценить значение новых предложений.";

        Note note1 = new Note("Заголовок 1",text1);
        Note note2 = new Note("Заголовок 2",text2);
        Note note3 = new Note("",text3);

        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);

        return args -> {
            noteRepository.saveAll(noteList);
        };

    }

}
