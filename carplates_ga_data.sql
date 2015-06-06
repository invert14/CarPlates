/*DELETE FROM users;
DELETE FROM registrationauthorities;
DELETE FROM owners;
DELETE FROM carplates;
DELETE FROM carplates_owners;
--DELETE FROM penalties;
--DELETE FROM insurances;*/

INSERT INTO users values
(1, 'Tomasz', 'Tomaszewski', 'a', 'GLOBAL', 'admin'),
(2, 'Łukasz', 'Łukasiewicz', 'a', 'LOCAL', 'user1'),
(3, 'Wojciech', 'Wojciechowski', 'a', 'LOCAL', 'user2'),
(4, 'Marian', 'Ubezpieczyciel', 'a', 'INSURANCE', 'ins_pzu'),
(5, 'Józef', 'Doradca', 'a', 'INSURANCE', 'ins_wrt');

INSERT INTO registrationauthorities values
(1, 'Gdynia', 'Prezydent Miasta Gdyni', '81-382', 'al. J. Piłsudskiego nr 52/54');

INSERT INTO carplates values 
(1, 'Nissan', 'Patrol', '1992-01-05 00:00:00', '1997-01-05 00:00:00', '2017-01-05 00:00:00', 'GA92934', '1M8GDM9AXKP042788', 1),
(2, 'Nissan', 'Sunny', '1997-09-10 00:00:00', '1998-01-20 00:00:00', '2018-01-20 00:00:00', 'GA1785L', '1ZVLT20A2V5134816', 1),
(3, 'Ford', 'Escort', '1998-11-01 00:00:00', '1998-11-01 00:00:00', '2018-11-01 00:00:00', 'GA77789', '1FMCU22E0VUD19092', 1),
(4, 'Mazda', '6', '1990-02-20 00:00:00', '2000-01-05 00:00:00', '2020-01-05 00:00:00', 'GA0021B', '1YVGE22C0V5625098', 1),
(5, 'Honda', 'Civic', '2002-06-01 00:00:00', '2005-02-03 00:00:00', '2025-02-03 00:00:00', 'GA99621', '1HGCD5526VA060425', 1),
(6, 'Fiat', 'Panda', '1999-08-05 00:00:00', '2010-04-10 00:00:00', '2030-04-10 00:00:00', 'GA7412A', '1FGCD5528VA024767', 1),
(7, 'Lamborghini', 'Gallardo', '2010-01-12 00:00:00', '2013-04-05 00:00:00', '2033-04-05 00:00:00', 'GA6655G', '1FGCD5530VA044844', 1),
(8, 'Peugeot', '409', '2000-10-10 00:00:00', '2003-05-07 00:00:00', '2023-05-07 00:00:00', 'GA44587', '1PFCD5531VA049714', 1),
(9, 'Renault', 'Twingo', '2010-03-02 00:00:00', '2012-10-01 00:00:00', '2032-10-01 00:00:00', 'GA12345', '1CTCD5531VA205380', 1);

INSERT INTO carplates_owners values
(1, '74120232155'),
(1, '84120932171'),
(2, '88300311192'),
(3, '88300311192'),
(4, '88300311192'),
(5, '83111009834'),
(6, '83111009834'),
(6, '84119232127'),
(7, '84119232127'),
(8, '84119232127'),
(9, '84119232127');
