--SELECT * FROM stud.knyga;

--pirmoji uzklausa
--SELECT Vardas,Pavarde FROM Stud.Skaitytojas 

--antroji uzklausa
-- SELECT Vardas,Pavarde FROM Stud.Skaitytojas
--	WHERE Nr = 1000

--trecioji uzklausa
-- SELECT vardas,Pavarde,Adresas FROM Stud.Skaitytojas
--	Where Adresas LIKE  '%Lenktoji%'

-- pastaba: Like for  patterns _ one symbol; % many symbols

-- ketvirtoji uzklausa
-- SELECT ak,adresas FROM Stud.Skaitytojas
--	WHERE EXTRACT(YEAR FROM gimimas) = 2001


--penktoji uzklausa
-- SELECT Count(distinct(K.*)),Count(distinct(E.Skaitytojas)),Count(distinct(E.ISBN)), Count(*)
--select *
-- FROM stud.knyga as K
-- Join stud.egzempliorius as E ON K.ISBN = E.ISBN;

-- Select count (K.*) 
-- FROM stud.Knyga as K;

-- sestoji uzklausa
--Select Avg(Puslapiai) FROM Stud.Knyga;
--Select Avg(verte) As "be null",
--avg(Case when verte Is null then 0 else verte end) as "su null"
--      	FROM Stud.Knyga;
--Select Cast(Case when verte Is null then 0 else verte end  AS Decimal(4,2)) as "su null"
--      	FROM Stud.Knyga
--	Order by verte desc;
-- Select round(Avg(Puslapiai),2) FROM Stud.Knyga


-- septintoji uzklausa
--SELECT Pavadinimas,Nr FROM Stud.Knyga,Stud.Egzempliorius
	-- WHERE Stud.Knyga.ISBN = Stud.Egzempliorius.ISBN

--astuntoji uzduotis
-- SELECT Stud.Egzempliorius.nr,Vardas,Pavarde FROM Stud.Egzempliorius,Stud.Skaitytojas
--	WHERE stud.egzempliorius.skaitytojas = stud.skaitytojas.nr

--devintoji uzduotis
--SELECT B.Pavarde,A.NR,A.ISBN
--FROM Stud.Egzempliorius A,Stud.Skaitytojas B
--	WHERE B.Nr = 1000 AND A.Skaitytojas = B.NR




--part  3 


--Kiekvienai dienai, kurią yra gimęs bent vienas skaitytojas, visų tą dieną gimusių skaitytojų skaičius:
/*select Gimimas, Count(*) 
from stud.skaitytojas
group by Gimimas
*/

-- Kiekvienai knygai visų jos egzempliorių skaičius:

--select stud.knyga.Isbn,count(*) as "bendras egzemplioriu skaicius"
--from Stud.knyga,stud.Egzempliorius
--where stud.knyga.isbn = stud.egzempliorius.ISBN
--group By stud.knyga.ISBN


--select stud.knyga.Isbn,count(*) as "bendras knygu skaicius" from Stud.knyga,stud.Egzempliorius
--where stud.knyga.isbn = stud.egzempliorius.ISBN
--group By stud.knyga.ISBN
--having count(*) > 3



-- Kiekvienai knygai vėliausiai paimto egzemplioriaus numeris, pavadinimas ir ISBN
/*
with VeliausiPaemimai (ISBN,veliausiasPaemimas) As (
	select ISBN, max(paimta)
       	from stud.egzempliorius
       	group by ISBN)

select NR,stud.knyga.ISBN,pavadinimas
from VeliausiPaemimai, stud.knyga, stud.egzempliorius
where stud.knyga.isbn = veliausiPaemimai.ISBN
and stud.Egzempliorius.paimta = VeliausiPaemimai.veliausiasPaemimas 
and stud.egzempliorius.isbn = veliausipaemimai.isbn;
*/



-- Visi knygų autorių vardai, kurie sutinkami tarp knygų autorių dažniau negu vidutiniškai:
/*
with KnyguSkaicius(vardas,skaicius) as(
select vardas, count(*) 
from stud.autorius, stud.knyga
where stud.autorius.isbn = stud.knyga.isbn
group by stud.autorius.vardas,stud.autorius.Pavarde),
KnyguVidurkis(vidurkis) as 
	(select avg(skaicius) 
		from KnyguSkaicius)

select KnyguSkaicius.vardas,KnyguSkaicius.skaicius,KnyguVidurkis.Vidurkis
from KnyguSkaicius, KnyguVidurkis
where KnyguSkaicius.skaicius > KnyguVidurkis.vidurkis;


WITH KnyguSkaiciai(Vardas, Skaicius)
     AS (SELECT Vardas, COUNT(ISBN)
         FROM Stud.Autorius
         GROUP BY Vardas),
     SkaiciuVidurkis(Vidurkis)
     AS (SELECT AVG(Skaicius)
       	FROM KnyguSkaiciai)
SELECT Vardas, Skaicius, Vidurkis
FROM  KnyguSkaiciai, SkaiciuVidurkis
WHERE Skaicius > Vidurkis
ORDER BY Vardas;*/




-------------------- 5 uzklausos pvz-------------------- 

--Visų lentelių stulpelių skaičiai
SELECT table_schema,table_name, Count(*)
FROM Information_Schema.Columns 
GROUP BY table_schema, table_name;


--Visų pastoviųjų (ne laikinųjų) realiųjų (ne virtualiųjų) lentelių stulpelių skaičiai:
SELECT a.table_schema,a.table_name, Count(*) as stulpeliai
FROM Information_Schema.Columns as a, information_schema.tables as b
WHERE a.table_schema= b.table_schema and a.table_name = b.table_name AND b.table_type = 'BASE TABLE'
GROUP BY a.table_schema, a.table_name;


--Visos lentelės, esančios schemoje stud:

Select table_schema,table_name
from information_schema.tables
where table_schema = 'stud';
