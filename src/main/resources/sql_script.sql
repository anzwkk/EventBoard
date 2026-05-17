create table events (
id serial primary key,
title varchar(200) not null,
event_date timestamp not null,
max_seats integer not null check (max_seats > 0)
);

create table participants (
id serial primary key,
event_id integer references events(id),
student_name varchar(100),
student_email varchar(255)
);