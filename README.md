# NotesApp

## 1. Задание
        
Текст [задания](./test_task.md) в корне проекта.

## 2. Описание

Приложение написано на ~~Groovy~~ Java с использованием _**Spring Framework**_. Основные компоненты приложения:

- **Spring boot.** &ensp;Для быстрой сборки и автоконфигурации.
- **Spring Data.** &ensp;Для работы с БД.
- **Spring Web.**  &ensp;Для создания локального сервера и диспетчера сервлетов.
- **Thymeleaf.**  &ensp;Шаблонизатор.
- **MySQL.**  &ensp;База данных.

Структура папки **_src_**:

```
└── src/ 
   └── main/
       ├──java/                                   
       │   └──com/                                 
       │     └──example/                         
       │        └──notesapp/                      
       │            ├──configurations/              # Каталог классов конфигураций.
       │            │   ├──MVCconfig.java           # Конфигурация ресурсов (Стили .css).
       │            │   └──StartConfiguration.java  # Конфигурация перед запуском приложения. Заполняем БД.
       │            ├──controllers/                 # Каталог контроллера.
       │            │   └──NoteController.java      # Контроллер для мапинга запросов.
       │            ├──entities/                    # Сущности. POJO-классы для связи с БД.
       │            │   └──Note.java                # Класс заметки (Note). 
       │            ├──repositories/                # Репозитории.
       │            │   └──NoteRepository.java      # Репозиторий для работы с Note в БД.                 
       │            ├──services/                    # Сервисы
       │            │   └──NoteService.java         # Сервис для работы с заметками.
       │            └──NotesappApplication.java     # Основной файл запуска приложения.
       └──resources/                              
          ├──static/                             
          │   └──css/                               # .css стили.
          │      ├──error.css                       # Стиль страницы ошибок.
          │      ├──main-page.css                   # Стиль главной страницы.
          │      ├──new-note.css                    # Стиль страницы создания/редактирования заметки.
          │      └──note.css                        # Стиль страницы просмотра заметки.
          ├──templates/                           
          │   ├──edit-note.html                     # Страница редактирования заметки.
          │   ├──error.html                         # Страница ошибки.
          │   ├──main-page.html                     # Главная страница.
          │   ├──new-note.html                      # Страница создания заметки.
          │   └──note.html                          # Страница просмотра заметки.
          └──application.properties                 # Файл свойств приложения.           

```

##3.Быстрый старт

1) Подгрузить зависимости Maven.
2) Создать схему в БД для хранения заметок с приложением.
3) Настроить основные [свойства](./src/main/resources/application.properties) приложения:

```properties
spring.application.name=note-application
server.port=8190
server.error.path=/error

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.generate-ddl=false
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/notesdatabase
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
```
Основные свойства:
- Порт приложения _**server.port**_. при необходимости поменять;
- URL ошибок **_server.error.path_**;
- URL БД _**spring.datasource.url**_. Указать имя схемы БД, которую создали в п.2;
- Указать логин _**spring.datasource.username**_ и пароль **_spring.datasource.password_** для доступа к БД;

4) Запускаем [NotesappApplication.java](./src/main/java/com/example/notesapp/NotesappApplication.java).
   После успешного запуска, перейдя в браузере по [ссылке](https://localhost:8190/app/main) мы попадем на главную страницу:

![Главная страница](/pic/main-page.PNG)

## PS.
Фронт ужасный, допилить не успел...
   
