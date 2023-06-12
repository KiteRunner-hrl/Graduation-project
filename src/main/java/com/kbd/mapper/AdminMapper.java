package com.kbd.mapper;

import com.kbd.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

  //修改用户权限
  @Update("update users set role = #{role} where id = #{id}")
  int changeUsersRole( @Param("id") Integer id, @Param("role") String role);

  //查询所有用户
  @Select("select * from users")
  List<Users> searchAllUser();

  //删除用户信息
  @Delete("delete from users where id = #{id}")
  void deleteUser(@Param("id") Integer id);
}
