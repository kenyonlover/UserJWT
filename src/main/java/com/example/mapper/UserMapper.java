package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getAllUsers();

    @Select("select * from user where id=#{id}")
    User findUserById(Integer userId);

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert({"insert into user(username,password) values(#{username},#{password})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Update("update user set password=#{password} where id=#{id}")
    Integer updatePwdById(User user);

}