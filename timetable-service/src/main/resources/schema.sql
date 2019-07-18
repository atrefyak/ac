DROP TABLE cities if EXISTS;
DROP TABLE routes if EXISTS;
DROP sequence if exists hibernate_sequence;

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE cities (id bigint NOT null, name varchar(255) NOT null, PRIMARY KEY (id));
CREATE TABLE routes (id bigint NOT null, arrival_time time NOT null, departure_time time NOT null, arrival_city bigint NOT null, departure_city bigint NOT null, PRIMARY KEY (id));