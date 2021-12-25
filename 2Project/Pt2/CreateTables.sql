CREATE TABLE pipe7709.Tiekejas (
       Nr BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH  30000 INCREMENT BY 1),
       Pavadinimas VARCHAR(20),
       TelefonoNumeris CHAR(12),
       ElPastas VARCHAR(320) NOT NULL,
       Puslapis VARCHAR(75),

       PRIMARY KEY (Nr),
       CONSTRAINT MaximalusTiekejuSkaicius CHECK (Nr < 40000 )
);

CREATE TABLE pipe7709.KavaArbata (
       Nr BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH  10000 INCREMENT BY 1),
       TiekejoNr BIGINT,
       Pavadinimas VARCHAR(56),
       TiekejoKaina FLOAT DEFAULT 0.00,
       PapildomaInformacija VARCHAR(256),

       CONSTRAINT MinKaina CHECK (TiekejoKaina >=0),
       CONSTRAINT FTiekejas FOREIGN KEY (TiekejoNr) REFERENCES pipe7709.Tiekejas ON DELETE SET NULL ON UPDATE CASCADE,
       PRIMARY KEY (Nr),
       CONSTRAINT MaximalusGerimuSkaicius CHECK (Nr < 20000 )
);

CREATE TABLE pipe7709.Iranga (
       Nr BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH  20000 INCREMENT BY 1),
       TiekejoNr BIGINT, 
       Pavadinimas VARCHAR(56),
       TiekejoKaina FLOAT DEFAULT 0.00,
       Talpa FLOAT,

       CONSTRAINT MinKaina CHECK (TiekejoKaina >=0),
       CONSTRAINT FTiekejas FOREIGN KEY (TiekejoNr) REFERENCES pipe7709.Tiekejas ON DELETE SET NULL ON UPDATE CASCADE,
       PRIMARY KEY (Nr),
       CONSTRAINT MaximalusIrangosSkaicius CHECK (Nr < 30000 )
);

CREATE TABLE pipe7709.Parduotuve (
       Nr BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH  40000 INCREMENT BY 1),
       Salis VARCHAR(56),
       Miestas VARCHAR(20),
       Gatve VARCHAR(30),
       DarboPradzia TIME NOT NULL,
       DarboPabaiga TIME NOT NULL,

       CONSTRAINT MaximalusParduotuviuSkaicius CHECK (Nr < 50000 ),
       CONSTRAINT DarboLaikas CHECK (DarboPradzia <= DarboPabaiga ),
       PRIMARY KEY (Nr)
);

CREATE TABLE pipe7709.ParduotuvesIranga (
       ParduotuvesNr INTEGER NOT NULL,
       IrangosNr INTEGER NOT NULL,
       Kiekis INTEGER DEFAULT 1,

       PRIMARY KEY (ParduotuvesNr,IrangosNr),
       CONSTRAINT KIEKIAI CHECK(Kiekis > 0),
       CONSTRAINT FIranga FOREIGN KEY (IrangosNr) REFERENCES pipe7709.Iranga ON DELETE SET NULL ON UPDATE CASCADE,
       CONSTRAINT FParduotuve FOREIGN KEY (ParduotuvesNr) REFERENCES pipe7709.Parduotuve ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE pipe7709.ParduotuvesGerimas (
       ParduotuvesNr INTEGER NOT NULL,
       GerimoNr INTEGER NOT NULL,
       Kiekis INTEGER DEFAULT 1,

       CONSTRAINT KIEKIAI CHECK(Kiekis > 0),
       PRIMARY KEY (ParduotuvesNr,GerimoNr),
       CONSTRAINT FTiekejas FOREIGN KEY (GerimoNr) REFERENCES pipe7709.KavaArbata ON DELETE SET NULL ON UPDATE CASCADE,
       CONSTRAINT FParduotuve FOREIGN KEY (ParduotuvesNr) REFERENCES pipe7709.Parduotuve ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE pipe7709.Pirkejai (
       Nr BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH  50000 INCREMENT BY 1),
       Vardas VARCHAR(100) NOT NULL,
       Pavarde VARCHAR(100) NOT NULL,
       PiniguKiekisPaskyroje FLOAT DEFAULT 0.00,
      
       PRIMARY KEY (Nr),
       CONSTRAINT MaximalusPirkejuSkaicius CHECK (Nr <= 60000 ),
       CONSTRAINT TeigiamasPiniguKiekisPaskyroje CHECK(PiniguKiekisPaskyroje > 0)
);

CREATE INDEX Index_KavaArbataPavadinimai ON pipe7709.KavaArbata(Pavadinimas);

CREATE INDEX Index_IrangaPavadinimai ON pipe7709.Iranga(Pavadinimas);

CREATE UNIQUE INDEX Index_ParduotuviuAdresai ON pipe7709.Parduotuve(Salis,Miestas,Gatve);

