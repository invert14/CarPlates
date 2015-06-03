CREATE TABLE insurances
(
  id serial NOT NULL,
  carplateid bigint UNSIGNED NOT NULL,
  insuranceNumber character varying(10),
  startDate timestamp,
  endDate timestamp,
  CONSTRAINT insurances_pk PRIMARY KEY (id)
);
