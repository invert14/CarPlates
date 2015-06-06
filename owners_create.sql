CREATE TABLE owners
(
  id serial,
  cityaddress character varying(255),
  firstname character varying(255),
  lastname character varying(255),
  pesel character varying(255),
  postcodeaddress character varying(255),
  streetaddress character varying(255),
  CONSTRAINT owners_pkey PRIMARY KEY (id)
);