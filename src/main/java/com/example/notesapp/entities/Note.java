package com.example.notesapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/*
    Объект заметки
*/

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Note {
    @Id
    @SequenceGenerator(name="note_sequence", sequenceName="NOTE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_sequence")
    private Long id;

    //Заголовок. Явно задаем тип данных "VARCHAR(20)"
    @Column(columnDefinition = "VARCHAR(20)")
    private String title;

    //Текст заметки. Явно задаем тип данных "TEXT", и запрещаем оставлять пустую заметку.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;


    public Note (String title, String text){
        this.title=title;
        this.text=text;
    }
}
