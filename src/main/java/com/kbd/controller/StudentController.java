package com.kbd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kbd.pojo.ClassRoom;
import com.kbd.pojo.Leave;
import com.kbd.pojo.SignIn;
import com.kbd.pojo.Users;
import com.kbd.service.StudentService;
import com.kbd.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student")
public class StudentController {
  private final String SUCCESS_CODE = "200";
  private final String ERROR_CODE = "202";

  @Resource
  StudentService studentService;
  @Resource
  UserService userService;

  //根据班级ID查询教师已创建的签到
  @GetMapping(value = "/searchSignIn", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> searchSignIn(@RequestParam("classId") Integer classId) {
    List<SignIn> signInList = studentService.findSignInListByClassId(classId);
    JSONArray jsonArray = new JSONArray(signInList);
    String result = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //学生签到
  @PostMapping(value = "/signIn", produces = "text/plain;charset=UTF-8")
  public String signIn(@RequestParam("classId") Integer classId, @RequestParam("sid") Integer sid, @RequestParam("userId") Integer userId, @RequestParam("username") String username, @RequestParam("finish") String finish) {
    JSONObject jsonObject = new JSONObject();
    studentService.addStudentSignIn(classId, sid, userId, username, finish);
    studentService.updateSignInCount(sid);
    jsonObject.put("message", "签到成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }

  //学生请假
  @PostMapping(value = "/leaveRequest", produces = "text/plain;charset=UTF-8")
  public String leaveRequest(@RequestParam("userId") Integer userId, @RequestParam("fullName") String fullName, @RequestParam("lessonName") String lessonName, @RequestParam("leaveType") String leaveType, @RequestParam("leaveReason") String leaveReason, @RequestParam("approveTeacher") String approveTeacher) {
    JSONObject jsonObject = new JSONObject();
    if (StringUtils.isEmpty(lessonName) || StringUtils.isEmpty(leaveType) || StringUtils.isEmpty(leaveReason) || StringUtils.isEmpty(approveTeacher)) {
      jsonObject.put("message", "请填写完整的表单信息");
      jsonObject.put("status", "202");
    }
    else {
      studentService.addLeave(userId, fullName, lessonName, leaveType, leaveReason, approveTeacher);
      jsonObject.put("message", "提交成功");
      jsonObject.put("status", SUCCESS_CODE);
    }
    return jsonObject.toJSONString();
  }

  //根据自己的ID查看请假申请
  @GetMapping(value = "/getLeave", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getLeaveById(@RequestParam("id") Integer id) {
    List<Leave> leaveList = studentService.getLeaveById(id);
    JSONArray jsonArray = new JSONArray(leaveList);
    String result = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  /**
   * 学生加入班级
   * @param classCode
   * @param id
   */
  @PostMapping(value = "/joinClass", produces = "text/plain;charset=UTF-8")
  public String joinClass(@RequestParam("classCode") String classCode, @RequestParam("userId") Integer id) {
    JSONObject jsonObject = new JSONObject();
    //获取学生ID
    Users users = userService.getUserByUserId(id);
    //判断邀请码是否正确
    int classId = studentService.checkClassCode(classCode);
    if (classId == 0) {
      jsonObject.put("message", "班级邀请码不存在");
      jsonObject.put("status", ERROR_CODE);
    }
    else {
      users.setClassId(classId);
      studentService.update(users);
      studentService.updateClassNum(classId);
      jsonObject.put("message", "加入班级成功");
      jsonObject.put("status", SUCCESS_CODE);
    }
    return jsonObject.toJSONString();
  }

  /**
   * 查看班级列表
   * @param id
   */
  @GetMapping(value = "/classList", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> classList(@RequestParam("id") Integer id) {
    List<ClassRoom> classRoomList = studentService.findClassById(id);
    JSONArray jsonArray = new JSONArray(classRoomList);
    HttpHeaders httpHeaders = new HttpHeaders();
    String result = jsonArray.toJSONString();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders,HttpStatus.OK);
  }
}
