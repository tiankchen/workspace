package com.chenchen.dao;

import com.chenchen.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserByID(int id);

    @Insert("INSERT INTO USER(name, dept, sex2) VALUES(#{name}, #{dept}, #{sex2, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler})")
    public boolean insetUser(User user);
}
