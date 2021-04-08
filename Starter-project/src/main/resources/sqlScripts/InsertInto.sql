--KREDIT
insert into "kredit"("id", "naziv", "oznaka", "opis")
values (nextval('kredit_seq'), 'Kes kredit', 'KK', 'Dodatna novcana sredstva koja banka odobrava.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (nextval('kredit_seq'), 'Stambeni kredit', 'SK', 'Stambeni kredit se odobrava za kupovinu stana ili kuce, kao i za izgradnju i adaptaciju stambenog objekta.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (nextval('kredit_seq'), 'Kes za refinansiranje', 'KREF', 'Omogucava refinansiranje kredita u drugoj banci.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (nextval('kredit_seq'), 'Kredit za auto', 'KA', 'Kredit koji se odobrava u cilju kupovine auta.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (nextval('kredit_seq'), 'Kes za namestaj', 'KN', 'Kredit koji se odobrava u cilju kupovine namestaja.');

insert into "kredit"("id", "naziv", "oznaka", "opis")
values (-100, 'Test', 'Test', 'Test');

--KLIJENT
insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (nextval('klijent_seq'), 'Petar', 'Petrovic', 11111, 1);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (nextval('klijent_seq'), 'Marko', 'Markovic', 22222, 2);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (nextval('klijent_seq'), 'Djordje', 'Djordjevic', 33333, 4);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (nextval('klijent_seq'), 'Zlata', 'Petkovic', 44444, 3);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (nextval('klijent_seq'), 'Marko', 'Kraljevic', 55555, 1);

insert into "klijent" ("id", "ime", "prezime", "broj_lk", "kredit")
values (-100, 'Test', 'Test', 1, 1);

--TIP RACUNA
insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (nextval('tip_racuna_seq'), 'Tekuci racun', 'TR', 'Tekuci racun je racun koji se koristi za izvrsavanje platnih transakcija tj uplata, prenos i isplata novcanih sredstava');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (nextval('tip_racuna_seq'), 'Devizni racun', 'DR', 'Devizni racun omogucava raspolaganje deviznim sredstvima');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (nextval('tip_racuna_seq'), 'Ziro racun', 'ZR', 'Ziro racun potrosacima sluzi za uplatu primanja poput rente ili honorara');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (nextval('tip_racuna_seq'), 'Stedni racun', 'SR', 'Stedni racun pomaze vam da stedite i da istovremeno imate raspoloziva sredstva');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (nextval('tip_racuna_seq'), 'Stedni racun za decu', 'SRD', 'Omogucava stednju kako bi se deci ostvarila bolja buducnost');

insert into "tip_racuna" ("id", "naziv", "oznaka", "opis")
values (-100, 'Test', 'Test', 'Test');

--RACUN
insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (nextval('racun_seq'), 'Racun 101', '101R', 'Racun je napravljen na zahtev klijenta', 1, 1);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (nextval('racun_seq'), 'Racun 202', '202R', 'Racun je napravljen na zahtev klijenta', 1, 3);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (nextval('racun_seq'), 'Racun 303', '303R', 'Racun je napravljen na zahtev klijenta', 2, 2);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (nextval('racun_seq'), 'Racun 404', '404R', 'Racun je napravljen na zahtev klijenta', 4, 4);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (nextval('racun_seq'), 'Racun 505', '505R', 'Racun je napravljen na zahtev klijenta', 1, 5);

insert into "racun" ("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
values (-100, 'Test', 'Test', 'Test', 1, 1);
