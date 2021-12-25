CREATE VIEW BendrasGerimuKiekisParduotuveje (ParduotuvesNr, Salis, Miestas, Gatve, PrekiuKiekis)
AS SELECT p.Nr, p.Salis, p.Miestas, p.Gatve, count(g.kiekis)
FROM pipe7709.ParduotuvesGerimas as g, pipe7709.Parduotuve as p
WHERE g.ParduotuvesNr = p.Nr
GROUP BY p.Nr;

CREATE VIEW BendraGerimuKainaParduotuveje (ParduotuvesNr, Salis, Miestas, Gatve, Kaina)
AS SELECT p.Nr, p.Salis, p.Miestas, p.Gatve, sum(k.TiekejoKaina)
FROM pipe7709.ParduotuvesGerimas as g, pipe7709.Parduotuve as p, pipe7709.KavaArbata as k
WHERE p.Nr = g.ParduotuvesNr AND k.Nr = g.GerimoNr
GROUP BY p.Nr;

CREATE VIEW BendraIrangosKainaParduotuveje (ParduotuvesNr, Salis, Miestas, Gatve, Kaina)
AS SELECT p.Nr, p.Salis, p.Miestas, p.Gatve, sum(i.TiekejoKaina)
FROM pipe7709.ParduotuvesIranga as g, pipe7709.Parduotuve as p, pipe7709.Iranga as i
WHERE p.Nr = g.ParduotuvesNr AND i.Nr = g.IrangosNr
GROUP BY p.Nr;

CREATE MATERIALIZED VIEW BendraPrekiuKainaParduotuveje (ParduotuvesNr, Salis, Miestas, Gatve, BendraKaina)
AS Select BP.ParduotuvesNr, BP.Salis, BP.Miestas, BP.Gatve, (BP.kaina+BI.kaina) AS Prekiu_Kaina
FROM BendraGerimuKainaParduotuveje as BP, BendraIrangosKainaParduotuveje as BI
Where BP.ParduotuvesNr = BI.ParduotuvesNr;

