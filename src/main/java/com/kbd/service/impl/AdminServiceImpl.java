package com.kbd.service.impl;

import com.kbd.mapper.AdminMapper;
import com.kbd.pojo.Users;
import com.kbd.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

  @Resource
  AdminMapper adminMapper;

  @Override
  public List<Users> searchAllUser() {
    return adminMapper.searchAllUser();
  }

  @Override
  public void deleteUser(Integer id) {
    adminMapper.deleteUser(id);
  }

  @Override
  public int changeUsersRole(Integer id, String role) {
    return adminMapper.changeUsersRole(id, role);
  }
}
