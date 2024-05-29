insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'monumento');
insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'chiesa');
insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'museo');

insert into attrazione (id, nome, urlimage, prezzo, tipo) values(nextval('attrazione_seq'), 'Colosseo', '\static\images\cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='monumento'));
insert into attrazione (id, nome, urlimage, prezzo, tipo) values(nextval('attrazione_seq'), 'Basilica san paolo', '\static\images\cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='chiesa'));
insert into attrazione (id, nome, urlimage, prezzo, tipo) values(nextval('attrazione_seq'), 'museo', '\static\images\cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='museo'));


