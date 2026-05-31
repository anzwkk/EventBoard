Веб-додаток для створення заходів та реєстрації учасників

### Технологічний стек:

* Java 21
* Jakarta EE (Servlet API)
* JSP / JSTL
* PostgreSQL
* JDBC
* Apache Tomcat 10+
* JUnit 5 / Mockito

### Інструкція зі встановлення:

#### 1. Налаштування бази даних (PostgreSQL)

* Встановіть та запустіть PostgreSQL. 
* Створіть базу даних:

`CREATE DATABASE event_board;`

* Виконайте SQL-скрипти для створення таблиць:

`create table events (
id serial primary key,
title varchar(200) not null,
event_date timestamp not null,
max_seats integer not null check (max_seats > 0)
);`

`create table participants (
id serial primary key,
event_id integer references events(id),
student_name varchar(100),
student_email varchar(255)
);`

#### 2. Налаштування Tomcat
* Встановіть Apache Tomcat 10+
* В IntelliJ IDEA перейдіть у Run -> Edit Configurations.
* Додайте конфігурацію Smart Tomcat.
* У налаштуваннях:
* Deployment directory: вкажіть шлях до папки src/main/webapp вашого проєкту.
* Context path: /Event_Board
* Server port: 8081

#### 3. Запуск проєкту
* Переконайтеся, що база даних запущена.
* Натисніть кнопку Run (зелений трикутник) у верхньому правому куті IDEA.
* Перейдіть у браузері за адресою

#### 4. Запуск тестів
Щоб перевірити бізнес-логіку проєкту, запустіть тести в класі EventServiceTest (натисніть правою кнопкою миші на файл -> Run 'EventServiceTest').