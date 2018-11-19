
CREATE TABLE IF NOT EXISTS bill (
  id SERIAL PRIMARY KEY,
  category_id bigint not null,
  description varchar(64) DEFAULT NULL,
  user_id bigint not NULL,
  bill_time timestamp without time zone,  -- 消费时间
  create_time timestamp without time zone,
  bill_type int not null  --  账单类型， 0 :支出   ，1 收入。    默认是两种
);
