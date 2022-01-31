insert into artikels(naam, prijs, artikelGroepId) values
('testartikel', 5, (select id from artikelGroepen where naam = 'groep1')),
('testartikeltwee', 10, (select id from artikelGroepen where naam = 'groep1')),
('testartikeldrie', 20, (select id from artikelGroepen where naam = 'groep2'));
