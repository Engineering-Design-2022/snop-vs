CREATE USER dev;
CREATE DATABASE db_dev;
GRANT ALL PRIVILEGES ON DATABASE db_dev TO dev;
\c db_dev;

DROP TABLE IF EXISTS number_of_people;
DROP TABLE IF EXISTS room;

CREATE TABLE room(
    id serial primary key,
    name varchar(30) not null,
    description text
);

CREATE TABLE number_of_people(
    id serial primary key,
    number integer not null,
    created_at timestamp with time zone not null default CURRENT_TIMESTAMP,
    room_id int not null,
    foreign key (room_id) references room(id)
);

insert into room (name, description) values ('311講義室','1階、4I教室');
insert into room (name, description) values ('312講義室','1階、5I教室');
insert into room (name, description) values ('情報基礎実験室','4階');
insert into room (name, description) values ('情報回路実験室','4階');

insert into number_of_people (number, room_id) values (7, 1);
insert into number_of_people (number, room_id) values (3, 2);
insert into number_of_people (number, room_id) values (10, 3);
insert into number_of_people (number, room_id) values (0, 4);