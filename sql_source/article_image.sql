CREATE TABLE IF NOT EXISTS article_image (
  id bigserial PRIMARY KEY,   --自增id
  content_id bigint not NULL,  --内容id
  image_src character varying(64)  -- 图片地址
);
