CREATE TABLE IF NOT EXISTS article (
  id bigserial PRIMARY KEY,   --自增id
  user_id bigint NOT NULL,    -- 用户id
  title character varying(128),  -- 文章title
  content_id bigint,  -- 文章id
  content_desc character varying(128),  -- 文章文章片段内容
  publish_time timestamp without time zone,  -- 发布时间
  view_count int, -- 查看次数
  article_type bigint,  -- 文章类型
  protect_type int , -- 文章模式 ：  私有， 公开， 部分人可看
  is_comment_able boolean, -- 是否允许留言
  tag  character varying(64)   --标签
);


-- insert into article (user_id, title,content_id,content_desc,publish_time,view_count) values(1,'test',1,'hello world','2018-04-02 10:23:54',1)