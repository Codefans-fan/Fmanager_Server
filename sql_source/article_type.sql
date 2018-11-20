CREATE TABLE IF NOT EXISTS article_type (
  id bigserial PRIMARY KEY,   --自增id
  type_name character varying(64) NOT NULL,
  description character varying(128)
);

insert into article_type(id,type_name,description) values(1,'新闻', '');
insert into article_type(id,type_name,description) values(2,'公告', '');
insert into article_type(id,type_name,description) values(3,'其他', '');