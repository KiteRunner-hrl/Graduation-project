package com.kbd.service.impl;

import com.kbd.mapper.UserMapper;
import com.kbd.pojo.Users;
import com.kbd.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UserService {
  @Resource
  UserMapper mapper;

  @Override
  public void registerUser(String fullName, String username, String password, String face_token) {
    Users users = new Users(0, fullName, username, 0, password, "user", face_token);
    if (mapper.addUser(users) <= 0) {
      throw new RuntimeException("用户信息添加失败");
    }
  }

  @Override
  public Users getUserByUsername(String username) {
    return mapper.getUserByUsername(username);
  }

  @Override
  public Users getUserByUserId(Integer id) {
    return mapper.getUserByUserId(id);
  }

  @Override
  public List<Users> getTeacherUser() {
    return mapper.getTeacherUser();
  }
}
