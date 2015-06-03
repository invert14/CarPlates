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
(1, 'Gdańsk', 'Prezydent Miasta Gdańska', '82-111', 'al. Guntera Grassa 54');

INSERT INTO owners values
(1, 'Gdańsk', 'Andrzej', 'Andrzejewicz', '14120234155', '82-432', 'Lelewela 12/8'),
(2, 'Gdańsk', 'Adam', 'Adamowicz', '83230932471', '82-123', 'Grunwaldzka 110A/20');

INSERT INTO carplates values 
(1, 'Lamborghini', 'Diablo', '1992-01-05 00:00:00', '1997-01-05 00:00:00', '2017-01-05 00:00:00', 'GD11114', '1ZVLT204SQ1134816', 1),
(2, 'Ferrari', 'Founder', '1997-09-10 00:00:00', '1998-01-20 00:00:00', '2018-01-20 00:00:00', 'GD1234L', '2ATLT20A2V5134816', 1);

INSERT INTO carplates_owners values
(1, 1),
(2, 2);
