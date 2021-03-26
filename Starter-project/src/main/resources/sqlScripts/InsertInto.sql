--KREDIT
insert into "kredit"("id", "naziv", "oznaka", "opis")
values (1, 'Kes kredit', 'KK', 'Dodatna novcana sredstva koja banka odobrava.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (2, 'Stambeni kredit', 'SK', 'Stambeni kredit se odobrava za kupovinu stana ili kuće, kao i za izgradnju i adaptaciju stambenog objekta.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (3, 'Kes za refinansiranje', 'KREF', 'Omogućava refinansiranje kredita u drugoj banci.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (4, 'Kredit za auto', 'KA', 'Kredit koji se odobrava u cilju kupovine auta.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (5, 'Kes za namestaj', 'KN', 'Kredit koji se odobrava u cilju kupovine namestaja.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (-100, 'Test', 'Test', 'Test');

--KLIJENT
insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (1, 'Petar', 'Petrovic', 11111, 1);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (2, 'Marko', 'Markovic', 22222, 2);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (3, 'Djordje', 'Djordjevic', 33333, 4);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (4, 'Zlata', 'Petkovic', 44444, 3);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (5, 'Marko', 'Kraljevic', 55555, 1);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (-100, 'Test', 'Test', 1, 1);

--TIP RACUNA
insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (1, 'Tekuci racun', 'TR', 'Tekući račun je račun koji se koristi za izvršavanje platnih transakcija – uplata, prenos i isplata novčanih sredstava');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (2, 'Devizni racun', 'DR', 'Devizni racun omogucava raspolaganje deviznim sredstvima');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (3, 'Ziro racun', 'ZR', 'Žiro račun potrošačima služi za uplatu primanja poput rente ili honorara');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (4, 'Stedni racun', 'SR', 'Štedni račun pomaže vam da štedite i da istovremeno imate raspoloživa sredstva');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (5, 'Stedni racun za decu', 'SRD', 'Omogucava stednju kako bi se deci ostvarila bolja buducnost');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (-100, 'Test', 'Test', 'Test');

--RACUN
insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (1, 'Racun 101', '101R', 'Racun je napravljen na zahtev klijenta', 1, 1);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (2, 'Racun 202', '202R', 'Racun je napravljen na zahtev klijenta', 1, 3);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (3, 'Racun 303', '303R', 'Racun je napravljen na zahtev klijenta', 2, 2);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (4, 'Racun 404', '404R', 'Racun je napravljen na zahtev klijenta', 4, 4);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (5, 'Racun 505', '505R', 'Racun je napravljen na zahtev klijenta', 1, 5);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (-100, 'Test', 'Test', 'Test', 1, 1);
