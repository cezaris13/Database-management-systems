# Database management systems (liet. Duomenų bazių valdymo sistemos)

This course was taken in Vilnius university in autumn semester 2021.

## Part 1

The database used for this tasks is biblio:

![Biblio](/Images/dbschema.png)

The tasks for this database:

1. Get all readers surname first letters.
1. The average of all the pages of all the books written by a specific author under the name and surname.
1. For each reader (name, surname), the number of authors of the books he has taken.
1. Name and surname of the author whose books are the most in the library. The number of all books and all copies of that author should also be presented.
1. All tables with more than one foreign key.


## Part 2

### Requirements for ER model:

1. The ER model must contain at least 3 (6 when working in pairs) interconnected, logically based entities.
1. An ER model must contain at least one 1:N and at least one N:M relationship.
1. An ER model must have at least one weak (conditional) and at least one strong (unconditional) relationship.
1. At least one composite entity attribute is used.
1. At least one relation has at least one attribute.


#### The ER schema

#### The DB schema

### Requirements for the database:

1. A DB consists of at least 4 logically based tables.
1. DB is in 4th normative form.
1. The tables have all the required primary and foreign keys.
1. Use at least 3 declarative requirements for values ​​using different predicates.
1. At least 2 default values ​​are defined.
1. At least 2 indexes are defined in the DB, of which at least one is unique and at least one is not.
1. At least 2 virtual tables are defined in the DB.
1. At least 1 materialized virtual table and its data update statement are defined in the DB.
1. DB ensures at least 2 subject rules (not value requirements) defined by triggers (row numbering is not a subject rule).
1. Triggers are used only to enforce data integrity requirements that cannot be enforced by other means of SQL.
1. DB contains at least 1 identity (number) which is provided automatically (column value generation or trigger).
1. When performing work for a pair of students , all quantitative requirements (minimum number of tables, etc.) are doubled .
1. All created DB objects must be meaningful, it is necessary to be able to explain their creation (definition) sentences.

### Requirements for the program:

1. At least one module must satisfy one of the following conditions:
    1. written in C/C++ programming language, using application SQL (only static SQL is sufficient),
    1. **written in the JAVA programming language, using JDBC, an interface that is not fully explained in the lectures,**
    1. the use of another programming language must be agreed in advance with the teacher of laboratory works.
1. The following basic actions (functions) of working with data must be implemented :
    1. data search;
    1. data entry;
    1. updating (changing) data;
    1. erasure (removal) of data.
1. It is not necessary to implement these actions (functions) to work with all DB data (tables), it is sufficient to implement them only for part of the DB data (tables).
1. At least one implemented function must use at least 2 interrelated tables.
1. The functions of working with data must implement actions specific to a specific subject area.
    1. For example, the data entry function (action) "Register a new library reader" is possible , but NOT "Enter a new row in the table.." ; possible - "Return the taken book" , but NOT "Change (or delete) a line" .
1. A single function may also require multiple SQL statements.
1. If, when implementing functions, it is necessary to enter an identity attribute that does not have a clear logical meaning, e.g. ID, it is necessary to display all the existing values ​​of that attribute together with the values ​​of the meaningful attributes before entering the value of the attribute. At least one such function must be implemented .
1. At least one data modification function must be implemented using several data modification statements, that is, a real transaction is used .
1. The application must be sufficiently resistant to SQL injections.
1. The program's user interface should be text-based, meaning a graphical user interface is completely unnecessary, but possible.
