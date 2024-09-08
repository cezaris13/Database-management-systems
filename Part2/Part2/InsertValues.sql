INSERT INTO pipe7709.Tiekejas (Pavadinimas,TelefonoNumeris,ElPastas,Puslapis)
VALUES
('UAB KAKAVA','865646236','jonas.jonauskas@kakava.lt',NUll),
('UAB EGZOTINE KAVA','861515236','Tomas.Tomauskas@kava.lt',NULL),
('UAB GERTUVES',NULL,'petras.jonauskas@Gertuves.lt','https://gertuve.lt');

INSERT INTO pipe7709.Parduotuve (Salis,Miestas,Gatve,DarboPradzia,DarboPabaiga)
VALUES
('Lietuva','Vilnius','Didlaukio g.59','08:00:00','21:00:00'),
('Lietuva','Kaunas','Svitrigailos g. 13','08:00:00','18:00:00'),
('Lietuva','Klaipeda','Paryziaus komunos 13','10:00:00','23:00:00');

INSERT INTO pipe7709.KavaArbata (TiekejoNr,Pavadinimas,TiekejoKaina,PapildomaInformacija)
VALUES
(30001,'Brazilija',5.99,'vidutiniskai skrudinta, uzpilti 95c temperaturos vandeniu'),
(30001,'Kuba',5.99,'vidutiniskai skrudinta, uzpilti 96c temperaturos vandeniu'),
(30002,'Etiopija',7.99,'vidutiniskai skrudinta, uzpilti 94c temperaturos vandeniu'),
(30000,'zalioji arbata ying-yang',6.99,'uzpilti 92c temperaturos vandeniu'),
(30000,'juodoji arbata Moringa',3.99,'uzpilti 93c temperaturos vandeniu');

INSERT INTO pipe7709.Iranga (TiekejoNr,Pavadinimas,TiekejoKaina,Talpa)
VALUES
(30000,'Smidge gertuve',10.00,1),
(30002,'maza grazi gertuve',5.99,0.5),
(30002,'Didele grazi gertuve',12.56,1.5);

INSERT INTO pipe7709.ParduotuvesIranga (ParduotuvesNr,IrangosNr,Kiekis)
VALUES
(40000,20002,30),
(40000,20001,5),
(40000,20000,1),
(40001,20000,16),
(40001,20002,32),
(40002,20001,12),
(40002,20000,18);

INSERT INTO pipe7709.ParduotuvesGerimas (ParduotuvesNr,GerimoNr,Kiekis)
VALUES
(40000,10002,25),
(40000,10000,40),
(40000,10003,100),
(40001,10001,17),
(40001,10002,30),
(40001,10004,32),
(40002,10001,23),
(40002,10000,65),
(40002,10003,50);
