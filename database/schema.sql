-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;
  
-- CREATE statements go here

-- TABLE location 
CREATE SEQUENCE location_location_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE location (
	location_id INTEGER DEFAULT nextval('location_location_id_seq'::regclass) NOT NULL,
	city varchar(30) NOT NULL,
	state varchar(30) NOT NULL,
	
	CONSTRAINT pk_location_location_id PRIMARY KEY (location_id)
);

-- TABLE brewery 
CREATE SEQUENCE brewery_brewery_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE brewery (
	brewery_id INTEGER DEFAULT nextval('brewery_brewery_id_seq'::regclass) NOT NULL,
	name varchar(30) NOT NULL,
	location_id integer NOT NULL,
	address varchar(200) NOT NULL,
	latitude REAL,
	longitude REAL,
	description TEXT NOT NULL,
	year_founded INTEGER NOT NULL,
	image_path varchar(30),
	
	CONSTRAINT pk_brewery_brewery_id PRIMARY KEY (brewery_id),
	CONSTRAINT fk_brewery_location FOREIGN KEY (location_id) REFERENCES location (location_id)
);

-- TABLE user
CREATE SEQUENCE users_users_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE users (
	user_id INTEGER DEFAULT nextval('users_users_id_seq'::regclass) NOT NULL,
	username varchar(30) NOT NULL,
	password varchar(80) NOT NULL,
	salt varchar(256) NOT NULL,
	role varchar(20) NOT NULL,
	email varchar(30),
	phone_number varchar(15),
	brewery_id INTEGER,
	
	CONSTRAINT pk_users_users_id PRIMARY KEY (user_id),
	CONSTRAINT fk_users_brewery FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id)

);

-- TABLE beer 
CREATE SEQUENCE beer_beer_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE beer (
	beer_id INTEGER DEFAULT nextval('beer_beer_id_seq'::regclass) NOT NULL,
	brewery_id INTEGER NOT NULL,
	name varchar(30) NOT NULL,
	description TEXT NOT NULL,
	beer_type varchar(20) NOT NULL,
	abv REAL NOT NULL,
	ibu INTEGER,
	glass_type varchar(30),
	
	CONSTRAINT pk_beer_beer_id PRIMARY KEY (beer_id),
	CONSTRAINT fk_beer_brewery FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id)
);

-- TABLE review 
CREATE SEQUENCE review_review_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE review (
	review_id INTEGER DEFAULT nextval('review_review_id_seq'::regclass) NOT NULL,
	beer_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	description TEXT NOT NULL,
	rating INTEGER NOT NULL,
	
	CONSTRAINT pk_review_review_id PRIMARY KEY (review_id),
	CONSTRAINT fk_review_beer FOREIGN KEY (beer_id) REFERENCES beer (beer_id),
	CONSTRAINT fk_review_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);



COMMIT;