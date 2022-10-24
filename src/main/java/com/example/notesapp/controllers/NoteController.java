package com.example.notesapp.controllers;

import com.example.notesapp.entities.Note;
import com.example.notesapp.repositories.NoteRepository;
import com.example.notesapp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


/*
    Контроллер для обработки запросов приложения
*/
@Controller
@RequestMapping("app")
public class NoteController {

    //Создаем ссылку на сервис заметок
    private final NoteService noteService;


    //Внедряем ссылку через конструктор
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    //Главная страница со списком всех заметок.
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){

     //Получаем через сервис все заметки. Кладем в модель в параметр "notes"
     model.addAttribute("notes", noteService.allNotes());
     return "main-page";
    }

    //Главная страница со списком отфильтрованных заметок, по наличию подстроки.
    //Подстроку получаем в теле параметром "searchString".
    @RequestMapping(value = "/main/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam("search")
                         String search)
    {

        //Получаем через сервис все заметки. Кладем в модель в параметр "notes"
        model.addAttribute("notes", noteService.search(search));
        model.addAttribute("search", search);
        return "main-page";
    }

    /*
        Запрос на создание новой заметки. Создание новой заметки делится на 2 этапа:
        1) GET-запрос на создание новой запетки, в котором мы создаем объект Note и
           кладем его в model. Затем перенаправляем в HTML-шаблон создания новой заметки;
        2) После заполнения шаблона, полученный объект Note отправляем через POST-запрос
           обратно в контроллер,где уже записываем его в БД через сервис и возвращаемся на
           главную страницу.
    */

    //1 этап:
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newNote(Model model){
        //Создаем Note и кладем его в model. Отправляем все в шаблон.
        Note note=new Note();
        model.addAttribute("note", note);
        return "new-note";
    }

    //2 этап:
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newNote(@ModelAttribute("note")Note note){

        //Получаем заполненный Note и сохраняем его в БД
        noteService.add(note);

        //Переходим через контроллер на главную страницу. Список заметок обновляется.
        return "redirect:/app/main";
    }

    /*
        Редактирование заметки делится так же на 2 этапа как и создание новой заметки.
        Отличие только в том, что мы не создаем пустой объект Note, а берем существующий
        из БД по id и во втором этапе используем метод сервиса edit(Note note), вместо
        new(Note note).
    */
    //1 этап:
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editNote(Model model,
                           @PathVariable("id")Long id)
    {
        //Получаем нужный Note из БД по id(переменная пути) и кладем его в model. Отправляем все в шаблон.
        Note note= noteService.getNote(id);
        model.addAttribute("note", note);
        return "edit-note";
    }

    //2 этап:
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editNote(@ModelAttribute Note note){

        //Получаем отредактированный Note и сохраняем его в БД
        noteService.edit(note);

        //Переходим через контроллер на главную страницу. Список заметок обновляется.
        return "redirect:/app/main";
    }

    //Удаление заметки по id(переменная пути).
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){

    //Удаляем заметку по id, используя сервис.
    noteService.delete(id);
    return "redirect:/app/main";
    }

    //Просмотр заметки по id(переменная пути).
    @RequestMapping(value = "/watch/{id}", method=RequestMethod.GET)
    public String watch(Model model,
                        @PathVariable("id")Long id){

        //Получаем заметку по id, используя сервис.
        Note note= noteService.getNote(id);

        //Добавляем note в модель.
        model.addAttribute("note", note);
        return "note";
    }

    //Если возникает ошибка, то открываем шаблон error.html
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        //Получаем код ошибки
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        //Передаем код ошибки через аттрибуты
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("error", statusCode);
        }
        return "error";
    }
}
