CREATE TABLE person (
	id_person integer NOT NULL,
	nm_name varchar NOT NULL,
	nn_age integer NOT NULL,
	pr_driving_license boolean DEFAULT FALSE,
	CONSTRAINT person_pk PRIMARY KEY (id_person)
);

CREATE TABLE car (
	id_car integer NOT NULL,
	nm_brand varchar NOT NULL,
	nm_model varchar NOT NULL,
	nn_price decimal NOT null,
	CONSTRAINT car_pk PRIMARY key (id_car)
);

CREATE TABLE person_car (
	id_person_car integer NOT NULL,
	id_person integer NOT NULL,
	id_car integer NOT NULL,
	CONSTRAINT person_car_pk PRIMARY KEY (id_person_car),
	CONSTRAINT person_car_unique UNIQUE (id_person, id_car),
	CONSTRAINT person_car_person_fk FOREIGN KEY (id_person) REFERENCES person(id_person),
	CONSTRAINT person_car_car_fk FOREIGN KEY (id_car) REFERENCES car(id_car)
);
