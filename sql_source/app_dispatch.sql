CREATE TABLE IF NOT EXISTS app_dispatch (
  id bigserial PRIMARY KEY,   --自增id
  app_id  character varying(64) NOT NULL,
  app_name character varying(64) NOT NULL,
  combo  character varying(256),     -- app icon  basic64
  app_type  integer NOT NULL,     -- app 类型    1 -  android  , 2 - ios
  app_bundle   character varying(64) NOT NULL,    -- ios bundle id ,  android - package id
  download_count  integer  NOT NULL default 0,
  download_link  character varying(128)  NOT NULL,
  app_version character varying(32),      --app build version
  version  integer ,    -- 内部版本管理
  appfile  character varying(64) NOT NULL  -- app 存放地址
);