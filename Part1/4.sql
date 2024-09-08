/*with autoriaus_knygu_sk (vardas,pavarde,sk) as (
	SELECT b.vardas,b.pavarde, count(a.isbn)
	FROM stud.egzempliorius as a, stud.autorius as b
	WHERE a.ISBN = b.ISBN
	GROUP By b.vardas,b.Pavarde),
maxi_autorius (vardas,pavarde, sk) as(
	SELECT vardas,pavarde, sk
       	FROM autoriaus_knygu_sk as a
	WHERE a.sk in (
		SELECT max(sk)
       		FROM autoriaus_knygu_sk)),
knygu_sarasas (vardas,pavarde,knyguSK) as (
	SELECT a.vardas,a.pavarde,count(a.isbn)
	FROM stud.autorius as a
	GROUP BY a.vardas,a.pavarde)

SELECT b.vardas,b.pavarde, sk, knyguSK
FROM maxi_autorius as b
Left Join knygu_sarasas as a
on lower(concat(b.vardas,b.pavarde)) = lower(concat(a.vardas,a.pavarde));*/

-- daugiau nei vidutiniskai, pagal metus-- vidurkis kiekvienu metu
with autorius_egzempliorius(vardas,pavarde,knyguSkaicius,egzemplioriuSkaicius,metai) as(
SELECT a.vardas,a.pavarde, Count(distinct(a.ISBN)), Count(e.Nr),k.metai
FROM stud.autorius as a,stud.egzempliorius as e, stud.knyga as k
WHERE a.ISBN = e.ISBN
AND a.ISBN = k.ISBN
GROUP BY a.Vardas,a.pavarde,k.metai),
autorius_vidurkis (vardas,pavarde,vidurkis)
as (Select vardas,pavarde, avg(egzemplioriuskaicius)
	from autorius_egzempliorius
	group by vardas,pavarde)


Select ae.*, av.vidurkis
From autorius_egzempliorius as ae, autorius_vidurkis as av
where ae.vardas = av.vardas
and ae.pavarde = av.pavarde
and ae.egzemplioriuSkaicius > av.vidurkis;

/*
select a.*,(select avg(b.knyguSkaicius) from autorius_egzempliorius as b) as "visu knygu vidurkis"
from autorius_egzempliorius as a
where a.knyguSkaicius > (select avg(b.knyguSkaicius)
	from autorius_egzempliorius as b);
*/
