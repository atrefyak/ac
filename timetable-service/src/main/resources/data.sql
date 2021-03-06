INSERT INTO cities (id, name) VALUES (1,'Kyiv');
INSERT INTO cities (id, name) VALUES (2,'Lviv');
INSERT INTO cities (id, name) VALUES (3,'Ternopil');
INSERT INTO cities (id, name) VALUES (4,'Chernivtsi');
INSERT INTO cities (id, name) VALUES (5,'Ivano-Frankivsk');
INSERT INTO cities (id, name) VALUES (6,'Zhytomyr');

INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (1, PARSEDATETIME('01:00', 'HH:mm'), PARSEDATETIME('10:00', 'HH:mm'), 1, 2);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (2, PARSEDATETIME('03:00', 'HH:mm'), PARSEDATETIME('11:00', 'HH:mm'), 1, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (3, PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('12:00', 'HH:mm'), 1, 4);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (4, PARSEDATETIME('05:00', 'HH:mm'), PARSEDATETIME('13:00', 'HH:mm'), 1, 5);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (5, PARSEDATETIME('07:00', 'HH:mm'), PARSEDATETIME('14:00', 'HH:mm'), 1, 6);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (6, PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('15:00', 'HH:mm'), 2, 1);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (7, PARSEDATETIME('11:00', 'HH:mm'), PARSEDATETIME('16:00', 'HH:mm'), 2, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (8, PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('17:00', 'HH:mm'), 2, 4);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (9, PARSEDATETIME('13:00', 'HH:mm'), PARSEDATETIME('18:00', 'HH:mm'), 2, 5);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (10, PARSEDATETIME('15:00', 'HH:mm'), PARSEDATETIME('19:00', 'HH:mm'), 2, 6);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (11, PARSEDATETIME('14:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'), 3, 1);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (12, PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('21:00', 'HH:mm'), 3, 2);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (13, PARSEDATETIME('18:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'), 3, 4);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (14, PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('13:00', 'HH:mm'), 3, 5);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (15, PARSEDATETIME('11:00', 'HH:mm'), PARSEDATETIME('14:00', 'HH:mm'), 3, 6);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (16, PARSEDATETIME('17:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'), 4, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (17, PARSEDATETIME('16:00', 'HH:mm'), PARSEDATETIME('21:00', 'HH:mm'), 4, 1);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (18, PARSEDATETIME('09:00', 'HH:mm'), PARSEDATETIME('19:00', 'HH:mm'), 4, 5);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (19, PARSEDATETIME('05:00', 'HH:mm'), PARSEDATETIME('09:00', 'HH:mm'), 4, 6);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (21, PARSEDATETIME('01:00', 'HH:mm'), PARSEDATETIME('07:00', 'HH:mm'), 5, 1);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (22, PARSEDATETIME('02:00', 'HH:mm'), PARSEDATETIME('8:00', 'HH:mm'), 5, 2);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (23, PARSEDATETIME('05:00', 'HH:mm'), PARSEDATETIME('10:00', 'HH:mm'), 5, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (24, PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('11:00', 'HH:mm'), 5, 4);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (25, PARSEDATETIME('13:00', 'HH:mm'), PARSEDATETIME('17:00', 'HH:mm'), 5, 6);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (26, PARSEDATETIME('09:00', 'HH:mm'), PARSEDATETIME('12:00', 'HH:mm'), 6, 1);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (27, PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('15:00', 'HH:mm'), 6, 2);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (28, PARSEDATETIME('20:00', 'HH:mm'), PARSEDATETIME('23:00', 'HH:mm'), 6, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (29, PARSEDATETIME('16:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'), 6, 4);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (30, PARSEDATETIME('15:00', 'HH:mm'), PARSEDATETIME('21:00', 'HH:mm'), 2, 3);
INSERT INTO routes (ID, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_CITY, ARRIVAL_CITY)
VALUES (31, PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'), 2, 3);

