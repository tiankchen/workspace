package com.chenchen.dao;

import com.chenchen.models.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserByID(int id);
}
