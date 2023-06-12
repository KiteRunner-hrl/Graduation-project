package com.kbd.service;

import com.kbd.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

  void registerUser(String fullName, String username, String password, String face_token);

  Users getUserByUsername(@Param("username") String username);

  Users getUserByUserId(@Param("userId") Integer id);

  List<Users> getTeacherUser();
}
