CREATE TABLE IF NOT EXISTS app_dispatch (
  id bigserial PRIMARY KEY,   --自增id
  user_id  bigint REFERENCES users(id),
  app_id  character varying(64) NOT NULL,
  app_name character varying(64) NOT NULL,
  combo  character varying(64),     -- app icon path
  app_type  integer NOT NULL,     -- app 类型    1 -  android  , 2 - ios
  app_bundle   character varying(64) NOT NULL,    -- ios bundle id ,  android - package id
  download_count  integer  NOT NULL default 0,
  app_version character varying(32),      --app build version
  version  integer  NOT NULL default 0,    -- 内部版本管理
  app_file  character varying(64) NOT NULL, -- app 存放地址
  create_time timestamp without time zone default CURRENT_TIMESTAMP
);