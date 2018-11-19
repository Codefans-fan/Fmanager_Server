CREATE TABLE IF NOT EXISTS user_role (
  id SERIAL PRIMARY KEY,
  description varchar(64) DEFAULT NULL,
  role_name varchar(64) DEFAULT NULL,
  pid int default 0,
  deleted boolean NOT NULL default false
);

INSERT INTO user_role (1,role_name, description,pid,deleted) VALUES ( 'admin', 'admin',0,false);
