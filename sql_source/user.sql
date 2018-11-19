-- Table: public.users

CREATE TABLE IF NOT EXISTS users
(
  id bigserial PRIMARY KEY,
  count integer,
  create_date timestamp without time zone,
  display_name character varying(64),
  email character varying(64) NOT NULL,
  last_login_time timestamp without time zone,
  login_date timestamp without time zone,
  mobile character varying(16),
  password character varying(64) NOT NULL,
  username character varying(64) NOT NULL,
  salt character varying(16) NOT NULL,
  deleted boolean NOT NULL DEFAULT false
  
);
ALTER TABLE users ADD CONSTRAINT mobile_constraint UNIQUE (mobile);
ALTER TABLE users ADD CONSTRAINT email_constraint UNIQUE (email);
-- ALTER TABLE users ADD CONSTRAINT username_constraint UNIQUE (username);

-- password:  dsa -> md5(5f039b4ef0058a1d652f13d612375a5b)-> BCrypt ->$2a$10$KgkrTI7/Wb1WETVyYaMWTep6SyZHsXey9fzFuFtwYZJvvltKfw4Cm
                                                                       
--INSERT INTO public.users(
--            id, count, create_date, display_name, email, last_login_time, 
--            login_date, mobile, password, username)
--    VALUES (1, 1, '2004-10-19 10:23:54', 'fky', '562867448@qq.com', '2004-10-19 10:23:54', 
--            '2004-10-19 10:23:54', '15201921469', '$2a$10$F2ig9Cf2bC4E.stUdBxGzOmvYpJFCgS3becwzEbgGHEWhXTC3LvRO', 'fky');


--$2a$10$pmkN5.zH5G8zcnGkAooKe.5J/LobUaJksuaDFkV4SkxtflEsO1nkK
