CREATE TABLE IF NOT EXISTS app_version (
  id bigserial PRIMARY KEY,   --自增id
  appref_id  bigint REFERENCES app_dispatch(id),
  download_count  integer  NOT NULL default 0,
  version  integer,    -- 内部版本管理
  appfile  character varying(64) NOT NULL  -- app 存放地址
);