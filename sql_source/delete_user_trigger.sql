

-- 触发器， 当删除用户时候  关联删除 用户关联角色

CREATE OR REPLACE FUNCTION delete_user() RETURNS trigger AS
$$BEGIN
   delete from user_role_ref where user_id=OLD.id;
   RETURN OLD;
END;$$ LANGUAGE plpgsql;


CREATE  TRIGGER delete_user AFTER DELETE  
ON users FOR EACH ROW EXECUTE PROCEDURE delete_user();

