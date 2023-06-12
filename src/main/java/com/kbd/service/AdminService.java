package com.kbd.service;

import com.kbd.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
  List<Users> searchAllUser();

  void deleteUser(Integer id);

  int changeUsersRole(Integer id, String role);
}
