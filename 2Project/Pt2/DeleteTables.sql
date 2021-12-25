DROP TABLE IF EXISTS pipe7709.KavaArbata CASCADE;
DROP TABLE IF EXISTS pipe7709.Iranga CASCADE;
DROP TABLE IF EXISTS pipe7709.Tiekejas CASCADE;
DROP TABLE IF EXISTS pipe7709.Parduotuve CASCADE;
DROP TABLE IF EXISTS pipe7709.ParduotuvesIranga CASCADE;
DROP TABLE IF EXISTS pipe7709.ParduotuvesGerimas CASCADE;
DROP TABLE IF EXISTS pipe7709.Pirkejai CASCADE;

DROP VIEW IF EXISTS BendrasGerimuKiekisParduotuveje;
DROP VIEW IF EXISTS BendraIrangosKainaParduotuveje;
DROP VIEW IF EXISTS BendraGerimuKainaParduotuveje;
DROP MATERIALIZED VIEW IF EXISTS BendraPrekiuKainaParduotuveje;

DROP INDEX IF EXISTS Index_KavaArbataPavadinimai;
DROP INDEX IF EXISTS Index_IrangaPavadinimai;
DROP INDEX IF EXISTS Index_ParduotuviuAdresai;

DROP TRIGGER IF EXISTS MaksimalusPrekiuSkaiciusParduotuveje1 ON pipe7709.ParduotuvesGerimas;
DROP TRIGGER IF EXISTS MaksimalusPrekiuSkaiciusParduotuveje2 ON pipe7709.ParduotuvesIranga;
DROP FUNCTION IF EXISTS MaksimalusPrekiuSkaiciusParduotuveje();

DROP TRIGGER IF EXISTS MaksimalusTiekejoTiekiamasPrekiuSkaicius1 ON pipe7709.KavaArbata;
DROP TRIGGER IF EXISTS MaksimalusTiekejoTiekiamasPrekiuSkaicius2 ON pipe7709.Iranga;
DROP FUNCTION IF EXISTS MaksimalusTiekejoPrekiuSkaicius();

