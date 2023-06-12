package com.kbd.controller;

import com.alibaba.fastjson.JSONObject;

import com.kbd.pojo.Users;
import com.kbd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
  @Resource
  UserService userService;
  private final String SUCCESS_CODE = "200";
  private final String ERROR_CODE = "202";

  /**
   * 用户登录
   * @param username
   * @param password
   */
  @PostMapping(value = "/login", produces = "text/plain;charset=UTF-8")
  public Object login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request) {
    JSONObject jsonObject = new JSONObject();
    //从数据库查询用户信息
    Users users = userService.getUserByUsername(username);
      if (users == null || !Objects.equals(password, users.getPassword())) {
        //登录失败
        jsonObject.put("message", "用户名称或密码有误");
        jsonObject.put("status", ERROR_CODE);
      }
      else {
        //登录成功
        if (Objects.equals(password, users.getPassword())) {
          request.getSession().setAttribute("userId", users.getId());
          jsonObject.put("message", "登录成功");
          jsonObject.put("status", SUCCESS_CODE);
          jsonObject.put("userId", users.getId());
          jsonObject.put("fullName", users.getFullName());
          jsonObject.put("role", users.getRole());
          jsonObject.put("classId", users.getClassId());
          jsonObject.put("username", users.getUsername());
          jsonObject.put("face_token", users.getFace_token());
        }
      }
    return jsonObject.toJSONString();
  }

  /**
   * 用户注册
   * @param fullName
   * @param username
   * @param password
   */
  @PostMapping(value = "/register", produces = "text/plain;charset=UTF-8")
  public Object register(@RequestParam("fullName")String fullName, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("face_token") String face_token) {
    JSONObject jsonObject = new JSONObject();
    //根据用户查询数据库中的用户，判断是否存在该用户
    if (userService.getUserByUsername(username) != null) {
      jsonObject.put("message", "用户已存在");
      jsonObject.put("status", ERROR_CODE);
    }
    else {
      userService.registerUser(fullName, username, password, face_token);
      jsonObject.put("message", "注册成功");
      jsonObject.put("status", SUCCESS_CODE);
    }
    return jsonObject.toJSONString();
  }

  //退出登录
  @GetMapping(value = "/logout", produces = "text/plain;charset=UTF-8")
  public String logout(HttpServletResponse response, HttpSession session) {
    JSONObject jsonObject = new JSONObject();
    Cookie cookieUsername = new Cookie("username", null);
    cookieUsername.setMaxAge(0);   //删除cookie
    response.addCookie(cookieUsername);
    session.invalidate(); //销毁session
    jsonObject.put("message", "操作成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }
}
