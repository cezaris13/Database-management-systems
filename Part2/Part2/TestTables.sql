Select * from pipe7709.Parduotuve;
Select * from pipe7709.KavaArbata;
Select * from pipe7709.Iranga;
Select * from pipe7709.Tiekejas;
Select * from pipe7709.ParduotuvesIranga;
Select * from pipe7709.ParduotuvesGerimas;


Select * from pipe7709.BendrasGerimuKiekisParduotuveje;
Select * from BendrasGerimuKiekisParduotuveje;
Select * from BendraGerimuKainaParduotuveje;
Select * from BendraIrangosKainaParduotuveje;
Select * from BendraPrekiuKainaParduotuveje;

SELECT * FROM pipe7709.Parduotuve;

UPDATE  pipe7709.ParduotuvesIranga 
SET Kiekis = 1000
WHERE ParduotuvesNr=40000 AND IrangosNR = 20002;

INSERT INTO pipe7709.KavaArbata (TiekejoNr,Pavadinimas,TiekejoKaina,PapildomaInformacija)
VALUES
(30001,'Brazilija',-0,'vidutiniskai skrudinta, uzpilti 95c temperaturos vandeniu');


INSERT INTO pipe7709.ParduotuvesIranga (ParduotuvesNr,IrangosNr,Kiekis)
VALUES
(40002,20002,0);


INSERT INTO pipe7709.ParduotuvesIranga (ParduotuvesNr,IrangosNr,Kiekis)
VALUES
(40002,20002,100);


INSERT INTO pipe7709.ParduotuvesIranga (ParduotuvesNr,IrangosNr,Kiekis)
VALUES
(40002,20002,1);

REFRESH MATERIALIZED VIEW BendraPrekiuKainaParduotuveje;


Select * from BendraPrekiuKainaParduotuveje;

Insert Into pipe7709.Parduotuve (Salis,Miestas,Gatve,DarboPradzia,DarboPabaiga)
Values
('Lietuva','Vilnius','Didlaukio g.59','08:00:00','12:00:00');

SELECT * FROM pipe7709.Parduotuve;
