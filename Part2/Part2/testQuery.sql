SELECT KA.*, PG.Kiekis as GerimoKiekis
FROM pipe7709.kavaArbata as KA, pipe7709.ParduotuvesGerimas as PG, pipe7709.Parduotuve as P
WHERE P.NR = 40000 AND P.Nr = PG.ParduotuvesNr AND PG.GerimoNr = KA.Nr;
