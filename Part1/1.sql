-- SELECT Pavarde FROM stud.Skaitytojas; -- all surnames

-- SELECT Distinct(Pavarde) FROM stud.Skaitytojas; -- unique Surnames

-- SELECT Left(Pavarde,1) FROM stud.Skaitytojas; -- lists all first letters(even if they are same)

-- SELECT DISTINCT(Right(Pavarde,1)) FROM stud.Skaitytojas; -- takes n-letters from right

-- SELECT Pavarde FROM stud.Skaitytojas WHERE Pavarde LIKE '%aitis%'; -- surname has 'aitis'

-- SELECT DISTINCT(Left(Pavarde,1)) AS "raides" FROM stud.Skaitytojas ORDER BY raides DESC;

-- SELECT CONCAT(A.Vardas,A.Pavarde) AS "pirmas skaitytojas" ,CONCAT(B.Vardas,B.Pavarde) AS "antras skaitytojas" FROM stud.Skaitytojas As A, stud.Skaitytojas As B where A.nr < B.Nr AND A.Pavarde = B.Pavarde;
-- or  A.vardas || ',' || A.Pavarde AS "pirmas skaitytojas"...

-- DECIMAL(6, 2) - 1234.5

SELECT DISTINCT(Upper(Left(Pavarde,1))) FROM stud.Skaitytojas;
