CREATE TABLE IF NOT EXISTS auth_client
(
  id bigserial PRIMARY KEY,
  create_date timestamp without time zone,
  client_name character varying(64),
  client_id character varying(64) NOT NULL,
  client_secret character varying(64) NOT NULL,
  grant_type character varying(64) NOT NULL
);

INSERT INTO auth_client(
	id, create_date, client_name, client_id, client_secret,grant_type)
	VALUES (1, '2018-11-18 00:00:00', 'test', 'testjwtclientid', 'XY7kmzoNzl100','password');