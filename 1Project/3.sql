/*SELECT c.vardas,c.pavarde, count(distinct(concat(b.vardas,b.pavarde))) as "autoriu sk"
FROM stud.egzempliorius as a, stud.autorius as b,stud.skaitytojas as c
WHERE a.isbn  = b.isbn AND a.skaitytojas IS Not Null AND c.nr = a.skaitytojas
GROUP BY c.vardas,c.pavarde
HAVING count(distinct(concat(b.vardas,b.pavarde))) > 5;*/



/*SELECT c.vardas,c.pavarde, count(distinct(lower(concat(b.vardas,b.pavarde)))) as "autoriu sk"
FROM stud.egzempliorius as a, stud.autorius as b,stud.skaitytojas as c
WHERE a.isbn  = b.isbn AND a.skaitytojas IS Not Null AND c.nr = a.skaitytojas
GROUP BY c.vardas,c.pavarde
ORDER BY 3 DESC;*/

/*SELECT c.vardas,c.pavarde, count(distinct(lower(concat(b.vardas,b.pavarde)))) as "autoriu sk"
FROM stud.egzempliorius as a, stud.autorius as b,stud.skaitytojas as c
WHERE a.isbn  = b.isbn AND a.skaitytojas IS Not Null AND c.nr = a.skaitytojas
GROUP BY c.vardas,c.pavarde
ORDER BY "autoriu sk" DESC;*/

/*SELECT s.vardas,s.pavarde,s.*, e.*,a.* --  count(distinct(lower(concat(a.vardas,a.pavarde)))) as "autoriu sk"
FROM stud.egzempliorius as e, stud.autorius as a,stud.skaitytojas as s
WHERE e.isbn  = a.isbn AND s.nr = e.skaitytojas AND */

SELECT s.vardas,s.pavarde, count(distinct(lower(concat(a.vardas,a.pavarde)))) as "autoriu sk"
FROM stud.egzempliorius as e, stud.autorius as a,stud.skaitytojas as s
WHERE e.isbn  = a.isbn AND s.nr = e.skaitytojas
GROUP BY s.nr;
