DROP DATABASE VetER;
CREATE DATABASE VetER;

\c veter;

CREATE TABLE type_of_pet(
        pet_type varchar NOT NULL,
        type_id integer NOT NULL,
        CONSTRAINT pk_type_of_pet_type_id PRIMARY KEY (type_id),
        CONSTRAINT ck_type_of_pet CHECK (pet_type IN ('DOG', 'CAT', 'BIRD', 'REPTILE', 'EXOTIC')),
        CONSTRAINT ck_type_id CHECK (type_id <= 5)
);  

CREATE TABLE pet(
        pet_id serial NOT NULL,
        pet_name varchar NOT NULL,
        pet_age integer NOT NULL,
        type_of_pet_id integer NOT NULL,
        CONSTRAINT pk_pet_pet_id PRIMARY KEY (pet_id)
);

CREATE TABLE owner(
        owner_id serial NOT NULL,
        first_name varchar NOT NULL,
        last_name varchar NOT NULL,
        CONSTRAINT pk_owner_owner_id PRIMARY KEY (owner_id)
);

CREATE TABLE pet_owner(
        owner_id integer NOT NULL,
        pet_id integer NOT NULL,
        CONSTRAINT pk_pet_owner_owner_id_pet_id PRIMARY KEY (owner_id, pet_id)
);

CREATE TABLE pet_procedure(
        procedure_id integer NOT NULL,
        procedure_type varchar,
        procedure_cost 
        CONSTRAINT procedure_procedure_id PRIMARY KEY (procedure_id),
        CONSTRAINT procedure_type CHECK (procedure_type IN ('RABIES VACINATION', 'EXAMINTATION', 'HEART WORM TEST', 'DENTAL', 'ANNUAL CHECKUP', 'TETANUS VACCINATION', 'XRAY', 'EYE WASH'))
);    

CREATE TABLE visit(
        visit_id serial,
        pet_id integer,
        procedure_id integer NOT NULL,
        visit_date date NOT NULL,
        CONSTRAINT visit_visit_id PRIMARY KEY (visit_id)
);

CREATE TABLE invoice(
        address_id integer NOT NULL,
        invoice_id integer NOT NULL,
);

CREATE TABLE address(
        address_id serial NOT NULL,
        owner_id integer NOT NULL,
        street_address varchar NOT NULL,
        city varchar NOT NULL,
        zip_code integer NOT NULL
);

INSERT INTO type_of_pet(pet_type, type_id)VALUES ('DOG', 1);
INSERT INTO type_of_pet(pet_type, type_id)VALUES ('CAT', 2);
INSERT INTO type_of_pet(pet_type, type_id)VALUES ('BIRD', 3);
INSERT INTO type_of_pet(pet_type, type_id)VALUES ('REPTILE', 4);
INSERT INTO type_of_pet(pet_type, type_id)VALUES ('EXOTIC', 5);

INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (1, 'FIDO', 5, 1);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (2, 'SPIKE', 10, 2);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (3, 'GOLDY', 40, 3);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (4, 'BUTCH', 2, 4);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (5, 'MISTER', 1, 5);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (6, 'SHADOW', 7, 1);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (7, 'SATAN', 8, 2);
INSERT INTO pet(pet_id, pet_name, pet_age, type_of_pet_id)VALUES (8, 'LEROY', 80, 3);

SELECT setval(pg_get_serial_sequence('pet', 'pet_id'), 8);

INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (1, 1, 8, '10-10-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (2, 2, 7, '10-15-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (3, 3, 6, '10-20-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (4, 4, 5, '10-22-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (5, 5, 4, '10-24-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (6, 6, 3, '10-30-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (7, 7, 2, '11-02-2017');
INSERT INTO visit(visit_id, pet_id, procedure_id, visit_date) VALUES (8, 8, 1, '11-13-2017');

SELECT setval(pg_get_serial_sequence('visit', 'visit_id'), 8);

INSERT INTO owner(owner_id, first_name, last_name) VALUES (1, 'Joe', 'Smith');
INSERT INTO owner(owner_id, first_name, last_name) VALUES (2, 'Becky', 'Blutowski');
INSERT INTO owner(owner_id, first_name, last_name) VALUES (3, 'Ben', 'Gay');
INSERT INTO owner(owner_id, first_name, last_name) VALUES (4, 'Nancy', 'Bradley');
INSERT INTO owner(owner_id, first_name, last_name) VALUES (5, 'Drew', 'Carey');

SELECT setval(pg_get_serial_sequence('owner', 'owner_id'), 5);

INSERT INTO pet_owner(owner_id, pet_id) VALUES (1, 1);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (1, 2);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (2, 3);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (2, 4);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (3, 5);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (4, 6);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (5, 7);
INSERT INTO pet_owner(owner_id, pet_id) VALUES (5, 8);

INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (1, 'RABIES VACINATION');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (2, 'EXAMINTATION');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (3, 'HEART WORM TEST');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (4, 'DENTAL');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (5, 'ANNUAL CHECKUP');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (6, 'TETANUS VACCINATION');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (7, 'XRAY');
INSERT INTO pet_procedure(procedure_id, procedure_type) VALUES (8, 'EYE WASH');

ALTER TABLE pet
ADD FOREIGN KEY (type_of_pet_id)
REFERENCES type_of_pet(type_id);

ALTER TABLE visit
ADD FOREIGN KEY (pet_id)
REFERENCES pet(pet_id);

ALTER TABLE visit
ADD FOREIGN KEY (procedure_id)
REFERENCES pet_procedure(procedure_id);

ALTER TABLE pet_owner
ADD FOREIGN KEY (pet_id)
REFERENCES pet(pet_id);

ALTER TABLE pet_owner
ADD FOREIGN KEY (owner_id)
REFERENCES owner(owner_id);

