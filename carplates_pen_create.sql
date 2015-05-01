CREATE TABLE penalties
(
  id serial,
  driverid bigint UNSIGNED NOT NULL,
  regionid text NOT NULL,
  title text,
  description text,
  points integer,
  startDate date,
  CONSTRAINT id PRIMARY KEY (id)
);