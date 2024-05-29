insert into tipologia_attrazione (id, nome) values(nextval('tipologia_attrazione_seq'), 'monumento');

insert into attrazione (id, nome, urlimage, prezzo, tipo) values(nextval('attrazione_seq'), 'Colosseo', '\static\images\cittaRoma.jpg', 18.00, (SELECT id FROM tipologia_attrazione WHERE nome='monumento'));
