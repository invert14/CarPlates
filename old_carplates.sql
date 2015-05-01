CREATE TABLE owners
(
  id serial NOT NULL,
  cityaddress character varying(255),
  firstname character varying(255),
  lastname character varying(255),
  pesel character varying(255),
  postcodeaddress character varying(255),
  streetaddress character varying(255),
  CONSTRAINT owners_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE owners
  OWNER TO jboss;


CREATE TABLE registrationauthorities
(
  id serial NOT NULL,
  cityaddress character varying(255),
  fullname character varying(255),
  postcodeaddress character varying(255),
  streetaddress character varying(255),
  CONSTRAINT registrationauthorities_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE registrationauthorities
  OWNER TO jboss;


CREATE TABLE users
(
  id serial NOT NULL,
  firstname character varying(255),
  lastname character varying(255),
  password character varying(255),
  userrole character varying(255),
  username character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO jboss;


CREATE TABLE carplates
(
  id serial NOT NULL,
  carbrand character varying(255),
  carmodel character varying(255),
  firstregistrationdate timestamp without time zone,
  registrationdate timestamp without time zone,
  registrationexpirationdate timestamp without time zone,
  registrationnumber character varying(255),
  vin character varying(255),
  registrationauthorityid bigint,
  CONSTRAINT carplates_pkey PRIMARY KEY (id),
  CONSTRAINT fk68dd8f1f20fdeef FOREIGN KEY (registrationauthorityid)
      REFERENCES registrationauthorities (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carplates
  OWNER TO jboss;


CREATE TABLE carplates_owners
(
  carplateid bigint NOT NULL,
  ownerid bigint NOT NULL,
  CONSTRAINT carplates_owners_pkey PRIMARY KEY (carplateid, ownerid),
  CONSTRAINT fk1f44c72e353268df FOREIGN KEY (carplateid)
      REFERENCES carplates (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk1f44c72e3bd348e1 FOREIGN KEY (ownerid)
      REFERENCES owners (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carplates_owners
  OWNER TO jboss;
  
CREATE TABLE penalties
(
  id serial NOT NULL,
  driverid bigint NOT NULL,
  regionid text NOT NULL,
  title text,
  description text,
  points integer,
  startDate date,
  CONSTRAINT penalties_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE penalties
  OWNER TO jboss;
