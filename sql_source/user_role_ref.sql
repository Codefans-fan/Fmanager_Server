CREATE TABLE IF NOT EXISTS user_role_ref (
  id bigserial PRIMARY KEY,
  user_id bigint NOT NULL,
  role_id int NOT NULL
);

insert into user_role_ref(id,user_id,role_id) values(1,1,1);