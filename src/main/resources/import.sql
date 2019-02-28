
INSERT INTO public.sectors(id, name) VALUES (1, 'LOS REYES');
INSERT INTO public.sectors(id, name) VALUES (2, 'LOS CIRUELITOS');
INSERT INTO public.sectors(id, name) VALUES (3, 'LOS SALADOS');
INSERT INTO public.sectors(id, name) VALUES (4, 'LOS PRADOS');
INSERT INTO public.sectors(id, name) VALUES (5, 'LA REAL');
INSERT INTO public.sectors(id, name) VALUES (6, 'LOS REYES 2DO');
INSERT INTO public.sectors(id, name) VALUES (7, 'BUENOS AIRES');


INSERT INTO public.leaders( id,code, name, phone) VALUES (1,'L01' ,'8297889554', 'RAMON GOMEZ');
INSERT INTO public.leaders( id,code, name, phone) VALUES (2,'L02' ,'8094557895', 'JUAN MARTINEZ');
INSERT INTO public.leaders( id,code, name, phone) VALUES (3,'L03' ,'8096652145', 'MARIA MOYA');
INSERT INTO public.leaders( id,code, name, phone) VALUES (4,'L04' ,'8091245621', 'JUANCHO MOTO');
INSERT INTO public.leaders( id,code, name, phone) VALUES (5,'L05' ,'8495546565', 'MARIO ACOSTA');


INSERT INTO public.voters(id, electoral_school, email, name, phone, registration_date, leader_id, sector_id)VALUES ('12345678', 'colegio fausto', 'jkk@gmail.com', 'ramon luna', '8209898899', '2020-01-12', 1, 1);
INSERT INTO public.voters(id, electoral_school, email, name, phone, registration_date, leader_id, sector_id)VALUES ('12222345678', 'colegio fausto', 'jkk@gmail.com', 'ramon luna', '8209898899', '2020-01-12', 2, 1);
INSERT INTO public.voters(id, electoral_school, email, name, phone, registration_date, leader_id, sector_id)VALUES ('2323232323', 'colegio fausto', 'jkk@gmail.com', 'ramon luna', '8209898899', '2020-01-12', 3, 1);
INSERT INTO public.voters(id, electoral_school, email, name, phone, registration_date, leader_id, sector_id)VALUES ('1323345678', 'colegio fausto', 'jkk@gmail.com', 'ramon luna', '8209898899', '2020-01-12', 4, 1);
INSERT INTO public.voters(id, electoral_school, email, name, phone, registration_date, leader_id, sector_id)VALUES ('512345678', 'colegio fausto', 'jkk@gmail.com', 'ramon luna', '8209898899', '2020-01-12', 5, 1);