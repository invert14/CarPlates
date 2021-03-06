-- 200820e3227815ed1756a6b531e7e0d2
INSERT INTO users values
(1, 'Tomasz', 'Tomaszewski', 'qwe123', 'GLOBAL', 'admin2'),
(2, 'Łukasz', 'Łukasiewicz', 'qwe123', 'LOCAL', 'user12'),
(3, 'Wojciech', 'Wojciechowski', 'qwe123', 'LOCAL', 'user22');

INSERT INTO registrationauthorities values
(1, 'Gdynia', 'Prezydent Miasta Gdyni', '81-382', 'al. J. Piłsudskiego nr 52/54');

INSERT INTO owners values
(1, 'Gdynia', 'Bartłomiej', 'Badamowski', '74120232155', '80-432', 'Lelewela 12/8'),
(2, 'Gdynia', 'Bartosz', 'Boduch', '84120932171', '80-123', 'Grunwaldzka 110A/20'),
(3, 'Gdynia', 'Bronisław', 'Burzyński', '88300311192', '82-500', 'Pomorska 1/2'),
(4, 'Gdynia', 'Bolesław', 'Bażyński', '83111009834', '88-400', 'Pomorska 1/2'),
(5, 'Gdynia', 'Beata', 'Bogusławska', '84119232127', '83-127', 'Chłopska 10/33');


INSERT INTO carplates values 
(1, 'Nissan', 'Patrol', '1992-01-05 00:00:00', '1997-01-05 00:00:00', NULL, 'GA92934', '1M8GDM9AXKP042788', 1),
(2, 'Nissan', 'Sunny', '1997-09-10 00:00:00', '1998-01-20 00:00:00', NULL, 'GA1785L', '1ZVLT20A2V5134816', 1),
(3, 'Ford', 'Escort', '1998-11-01 00:00:00', '1998-11-01 00:00:00', NULL, 'GA77789', '1FMCU22E0VUD19092', 1),
(4, 'Mazda', '6', '1990-02-20 00:00:00', '2000-01-05 00:00:00', NULL, 'GA0021B', '1YVGE22C0V5625098', 1),
(5, 'Honda', 'Civic', '2002-06-01 00:00:00', '2005-02-03 00:00:00', NULL, 'GA99621', '1HGCD5526VA060425', 1),
(6, 'Fiat', 'Panda', '1999-08-05 00:00:00', '2010-04-10 00:00:00', NULL, 'GA74120A', '1FGCD5528VA024767', 1),
(7, 'Lamborghini', 'Gallardo', '2010-01-12 00:00:00', '20013-04-05 00:00:00', NULL, 'GA6655G', '1FGCD5530VA044844', 1),
(8, 'Peugeot', '409', '2000-10-10 00:00:00', '2003-05-07 00:00:00', NULL, 'GA44587', '1PFCD5531VA049714', 1),
(9, 'Renault', 'Twingo', '2010-03-02 00:00:00', '2012-10-01 00:00:00', NULL, 'GA12345', '1CTCD5531VA205380', 1);

INSERT INTO carplates_owners values
(1, 1),
(1, 2),
(2, 3),
(3, 3),
(4, 3),
(5, 4),
(6, 4),
(6, 5),
(7, 5),
(8, 5),
(9, 5);
