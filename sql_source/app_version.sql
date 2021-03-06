CREATE TABLE IF NOT EXISTS app_version (
  id bigserial PRIMARY KEY,   --自增id
  appref_id  bigint REFERENCES app_dispatch(id),
  download_count  integer  NOT NULL default 0,
  version  integer,    -- 内部版本管理
  app_file  character varying(64) NOT NULL,  -- app 存放地址
  create_time timestamp without time zone default CURRENT_TIMESTAMP
);