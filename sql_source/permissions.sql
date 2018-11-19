CREATE TABLE IF NOT EXISTS permissions (
  id bigserial PRIMARY KEY,
  permission_name character varying(64) NOT NULL,
  description character varying(128) NOT NULL,
  rid int default 0,
  deleted boolean NOT NULL default false
);

insert into permissions (id,permission_name,description,rid,deleted) values (1,'all','admin permission',7,false);