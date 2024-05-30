insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'monumento');
insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'chiesa');
insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'museo');

insert into attrazione (id, nome, urlimage, prezzo, tipo, quartiere) values(nextval('attrazione_seq'), 'Colosseo', '/images/cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='monumento'), 'Rione Monti');
insert into attrazione (id, nome, urlimage, prezzo, tipo, quartiere) values(nextval('attrazione_seq'), 'Basilica san paolo', '/images/cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='chiesa'),'San Paolo');
insert into attrazione (id, nome, urlimage, prezzo, tipo, quartiere) values(nextval('attrazione_seq'), 'museo', '/images/cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='museo'), 'Museo');





insert into ristorante (id, nome, urlimage, descrizione, tipo, indirizzo,apertura,chiusura) values(nextval('ristorante_seq'), 'Pizza', '/images/cittaRoma.jpg', 'Ristorante italiano', 'Pizzeria', 'Via Roma', '17:30', '00:30');
insert into ristorante (id, nome, urlimage, descrizione, tipo, indirizzo,apertura,chiusura) values(nextval('ristorante_seq'), 'Pasta', '/images/cittaRoma.jpg', 'Ristorante it', 'Osteria', 'Via','17:30', '00:30');
insert into ristorante (id, nome, urlimage, descrizione, tipo, indirizzo,apertura,chiusura) values(nextval('ristorante_seq'), 'Nachos', '/images/cittaRoma.jpg', 'Ristorante messicano', 'Messicano', 'Via','17:30', '00:30');
insert into ristorante (id, nome, urlimage, descrizione, tipo, indirizzo,apertura,chiusura) values(nextval('ristorante_seq'), 'Sushi', '/images/cittaRoma.jpg', 'Ristorante cinese', 'Giapponese', 'Via','17:30', '00:30');












