CREATE FUNCTION MaksimalusPrekiuSkaiciusParduotuveje()
RETURNS TRIGGER AS
$$
DECLARE
    _GerimuSk INTEGER;
    _IrangosSk INTEGER;
BEGIN
    SELECT SUM(p.Kiekis) INTO _GerimuSK
    FROM pipe7709.ParduotuvesGerimas as p
    WHERE p.ParduotuvesNr = NEW.ParduotuvesNr;

    SELECT SUM(p.Kiekis) INTO _IrangosSK
    FROM pipe7709.ParduotuvesIranga as p
    WHERE p.ParduotuvesNr = NEW.ParduotuvesNr;

    IF(_GerimuSk+_IrangosSK) >=200
    THEN
        RAISE EXCEPTION 'Pasiektas maksimalus prekiu skaicius parduotuveje';
    END IF;
    RETURN NEW;
END;$$
LANGUAGE plpgsql;

CREATE TRIGGER MaksimalusPrekiuSkaiciusParduotuveje1
BEFORE INSERT OR UPDATE
ON pipe7709.ParduotuvesGerimas
FOR EACH ROW
EXECUTE PROCEDURE MaksimalusPrekiuSkaiciusParduotuveje();

CREATE TRIGGER MaksimalusPrekiuSkaiciusParduotuveje2
BEFORE INSERT OR UPDATE
ON pipe7709.ParduotuvesIranga
FOR EACH ROW
EXECUTE PROCEDURE MaksimalusPrekiuSkaiciusParduotuveje();

CREATE FUNCTION MaksimalusTiekejoPrekiuSkaicius()
RETURNS TRIGGER AS
$$
DECLARE
    _GerimuSk INTEGER;
    _IrangosSk INTEGER;
BEGIN
    SELECT COUNT(*) INTO _GerimuSK
    FROM pipe7709.KavaArbata AS k
    WHERE k.TiekejoNr = NEW.TiekejoNr;

    SELECT COUNT(*) INTO _IrangosSk
    FROM pipe7709.Iranga as i
    WHERE i.TiekejoNr = NEW.TiekejoNr;

    IF(_GerimuSk+_IrangosSK) >= 200
    THEN
        RAISE EXCEPTION 'Pasiektas maksimalus prekiu skaicius tiekejui';
    END IF;
    RETURN NEW;
END;$$
LANGUAGE plpgsql;

CREATE TRIGGER MaksimalusTiekejoTiekiamasPrekiuSkaicius1
BEFORE INSERT OR UPDATE
ON pipe7709.Iranga
FOR EACH ROW
EXECUTE PROCEDURE MaksimalusTiekejoPrekiuSkaicius();

CREATE TRIGGER MaksimalusTiekejoTiekiamasPrekiuSkaicius2
BEFORE INSERT OR UPDATE
ON pipe7709.KavaArbata
FOR EACH ROW
EXECUTE PROCEDURE MaksimalusTiekejoPrekiuSkaicius();

