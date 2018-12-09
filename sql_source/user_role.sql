CREATE TABLE IF NOT EXISTS user_role (
  id SERIAL PRIMARY KEY,
  description varchar(64) DEFAULT NULL,
  role_name varchar(64) DEFAULT NULL,
  pid int default 0,
  deleted boolean NOT NULL default false
);

INSERT INTO user_role (id,role_name, description,pid,deleted) VALUES (1, 'admin', 'admin',0,false);

INSERT INTO user_role (id,role_name, description,pid,deleted) VALUES (2, 'user', 'register user',0,false);

INSERT INTO user_role (id,role_name, description,pid,deleted) VALUES (3, 'visitor', 'not register user',0,false);