CREATE TABLE IF NOT EXISTS role_permissions_ref (
  id bigserial PRIMARY KEY,
  role_id bigint NOT NULL,
  permission_id bigint NOT NULL
);
insert into role_permissions_ref (id, role_id,permission_id) values (1,1,1);