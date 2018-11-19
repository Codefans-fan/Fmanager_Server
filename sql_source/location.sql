CREATE TABLE IF NOT EXISTS location (
  id bigserial PRIMARY KEY,   --自增id
  name character varying(64)  -- 地域
);

insert into location name value 'China';