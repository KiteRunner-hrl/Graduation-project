package com.kbd.mapper;

import com.kbd.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

  //用户注册
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into users(fullName, username, classId, password, role, face_token) values(#{fullName}, #{username}, #{classId}, #{password}, #{role}, #{face_token})")
  int addUser(Users users);

  //根据用户名查询用户
  @Select("select * from users where username = #{username} limit 1")
  Users getUserByUsername(String username);

  //根据用户ID查询用户
  @Select("select * from users where id = #{id} limit 1")
  Users getUserByUserId(Integer id);

  @Select("select * from users where role = 'teacher' ")
  List<Users> getTeacherUser();
}
