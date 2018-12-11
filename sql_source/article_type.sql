CREATE TABLE IF NOT EXISTS article_type (
  id bigserial PRIMARY KEY,   --自增id
  type_name character varying(64) NOT NULL,
  description character varying(128),
  deleted boolean NOT NULL DEFAULT false
);

insert into article_type(id,type_name,description) values(1,'新闻', 'news');
insert into article_type(id,type_name,description) values(2,'公告', 'note');
insert into article_type(id,type_name,description) values(3,'其他', 'others');