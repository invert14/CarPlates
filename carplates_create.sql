/*--DROP TABLE owners;
--DROP TABLE registrationauthorities;
--DROP TABLE users;
--DROP TABLE carplates;
--DROP TABLE carplates_owners;
--DROP TABLE penalties;*/




CREATE TABLE registrationauthorities
(
  id serial,
  cityaddress character varying(255),
  fullname character varying(255),
  postcodeaddress character varying(255),
  streetaddress character varying(255),
  CONSTRAINT registrationauthorities_pkey PRIMARY KEY (id)
);

CREATE TABLE users
(
  id serial,
  firstname character varying(255),
  lastname character varying(255),
  password character varying(255),
  userrole character varying(255),
  username character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
);


CREATE TABLE carplates
(
  id serial,
  carbrand character varying(255),
  carmodel character varying(255),
  firstregistrationdate timestamp,
  registrationdate timestamp,
  registrationexpirationdate timestamp,
  registrationnumber character varying(255),
  vin character varying(255),
  registrationauthorityid bigint UNSIGNED NOT NULL,
  CONSTRAINT carplates_pkey PRIMARY KEY (id),
  CONSTRAINT fk68dd8f1f20fdeef FOREIGN KEY (registrationauthorityid)
	REFERENCES registrationauthorities(id) ON DELETE CASCADE
);


CREATE TABLE carplates_owners
(
  carplateid bigint UNSIGNED NOT NULL,
  ownerpesel character varying(255),
  CONSTRAINT carplates_owners_pkey PRIMARY KEY (carplateid, ownerpesel),
  CONSTRAINT fk1f44c72e353268df FOREIGN KEY (carplateid)
      REFERENCES carplates (id) ON DELETE CASCADE
);
