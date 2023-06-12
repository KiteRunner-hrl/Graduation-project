package com.kbd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kbd.pojo.Users;
import com.kbd.service.AdminService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
  private final String SUCCESS_CODE = "200";
  private final String ERROR_CODE = "202";

  @Resource
  AdminService adminService;

  //查看所有用户
  @GetMapping(value = "/findAllUsers", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> findAllUsers() {
    List<Users> usersList = adminService.searchAllUser();
    JSONArray jsonArray = new JSONArray(usersList);
    String result = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //修改用户权限
  @PostMapping(value = "/changeUserRole", produces = "text/plain;charset=UTF-8")
  public String changeUserRole(@RequestParam("id") Integer id, @RequestParam("role") String role) {
    JSONObject jsonObject = new JSONObject();
    adminService.changeUsersRole(id, role);
    jsonObject.put("message", "修改成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }
}
