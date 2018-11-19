-- 

CREATE TABLE IF NOT EXISTS category (
  id SERIAL PRIMARY KEY,
  name varchar(64) not null,
  description varchar(64) DEFAULT NULL,
  user_id bigint DEFAULT NULL  --  创建这个类别的用户， 判断类别是否私有
);
