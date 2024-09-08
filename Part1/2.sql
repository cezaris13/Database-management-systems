-- SELECT case when round(AVG(A.Puslapiai),2) < 350 then 'daug puslapiu' else 'vidutiniskai puslapiu' end as "puslapiu kiekis" FROM stud.Autorius, stud.Knyga A
-- WHERE Vardas = 'Pijus' AND Pavarde = 'Jonaitis' AND stud.autorius.ISBN = A.ISBN;

SELECT *
FROM stud.Knyga
join stud.Egzempliorius
on Stud.egzempliorius.ISBN = stud.Knyga.ISBN
left join stud.SKaitytojas
on stud.Egzempliorius.Skaitytojas = stud.Skaitytojas.Nr
where stud.Egzempliorius.NR = 32101;
-- Not in; In; Like; not like;Exists;Is null; Is Not Null; any

/*
SELECT cast((AVG(A.Puslapiai),2) As Decimal(5,1)) FROM stud.Autorius, stud.Knyga As A
WHERE lower(Vardas) = 'pijus' AND upper(Pavarde) = 'JONAITIS' AND stud.autorius.ISBN = A.ISBN;
*/
