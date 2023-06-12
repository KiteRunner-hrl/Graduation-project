package com.kbd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kbd.pojo.*;
import com.kbd.service.TeacherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
  @Resource
  TeacherService teacherService;
  private final String SUCCESS_CODE = "200";
  private final String ERROR_CODE = "202";

  //创建签到
  @PostMapping(value = "/createSignIn", produces = "text/plain;charset=UTF-8")
  public String createSignIn(@RequestParam("teacherId") Integer teacherId, @RequestParam("classId") Integer classId, @RequestParam("lessonName") String lessonName, @RequestParam("time") Integer time) {
    JSONObject jsonObject = new JSONObject();
    teacherService.createSignIn(teacherId, classId, lessonName, time);
    jsonObject.put("message", "创建成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }

  //根据ID查看签到状态
  @GetMapping(value = "/searchSignIn", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> searchSignIn(@RequestParam("id") Integer id) {
    List<SignIn> signInList = teacherService.getSignInById(id);
    JSONArray jsonArray = new JSONArray(signInList);
    String jsonResult = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(jsonResult, httpHeaders, HttpStatus.OK);
  }

  //根据创建的签到活动表ID查询已经签到的用户
  @GetMapping(value = "/searchSignInStudent", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getIsSignInUser(@RequestParam("sid") Integer sid) {
    List<StudentSignIn> signInList = teacherService.findAllSignInStudent(sid);
    JSONArray jsonArray = new JSONArray(signInList);
    String jsonResult = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(jsonResult, httpHeaders, HttpStatus.OK);
  }

  //创建班级
  @PostMapping(value = "/createClass", produces = "text/plain;charset=UTF-8")
  public Object createClass(@RequestParam("className") String className, @RequestParam("teacherId") Integer teacherId, @RequestParam("teacherName") String teacherName) {
    JSONObject jsonObject = new JSONObject();
    if (teacherService.findClass(className) != null) {
      jsonObject.put("message", "班级已存在");
      jsonObject.put("status", ERROR_CODE);
    } else {
      teacherService.createClass(className, teacherId, teacherName);
      jsonObject.put("message", "创建成功");
      jsonObject.put("status", SUCCESS_CODE);
    }
    return jsonObject.toJSONString();
  }

  //根据ID查询已创建的班级
  @GetMapping(value = "/searchAllClass", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> findAllClass(@RequestParam("teacherId") Integer id) {
    List<ClassRoom> classRooms = teacherService.findClassByTeacherId(id);
    JSONArray jsonArray = new JSONArray(classRooms);
    String jsonResult = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(jsonResult, httpHeaders, HttpStatus.OK);
  }

  //解散班级
  @PostMapping(value = "/deleteClass", produces = "text/plain;charset=UTF-8")
  public String deleteClass(@RequestParam("classID") Integer id) {
    teacherService.deleteClass(id);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("message", "删除成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }

  //根据ID查找请假审批
  @GetMapping(value = "/getLeave", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getLeaveById(@RequestParam("id") Integer id) {
    List<Leave> leaveList = teacherService.getLeaveById(id);
    JSONArray jsonArray = new JSONArray(leaveList);
    String result = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //根据教师名字查找请假审批
  @GetMapping(value = "/getLeaveByTeacherName", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getLeaveByTeacherName(@RequestParam("teacherName") String teacherName) {
    List<Leave> leaveList = teacherService.findStudentByTeacherName(teacherName);
    JSONArray jsonArray = new JSONArray(leaveList);
    String result = jsonArray.toJSONString();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //请假审批(通过)
  @PostMapping(value = "/leaveApprovalPass", produces = "text/plain;charset=UTF-8")
  public String leaveApprovalPass(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
    JSONObject jsonObject = new JSONObject();
    teacherService.approveLeavePass(id, status);
    jsonObject.put("message", "审批成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }

  //请假审批(不通过)
  @PostMapping(value = "/leaveApprovalReject", produces = "text/plain;charset=UTF-8")
  public String leaveApprovalReject(@RequestParam("id") Integer id, @RequestParam("status") Integer status, @RequestParam("reject") String reject) {
    JSONObject jsonObject = new JSONObject();
    teacherService.updateStatusReject(id, status, reject);
    jsonObject.put("message", "审批成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }

  //根据教师名称查询请假审批通过的学生
  @GetMapping(value = "/getLeaveStudent", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getLeaveStudent(@RequestParam("teacherName") String teacherName) {
    List<Leave> classDetail = teacherService.findStudent(teacherName);
    HttpHeaders httpHeaders = new HttpHeaders();
    JSONArray jsonArray = new JSONArray(classDetail);
    String result = jsonArray.toJSONString();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //审批结束
  //查看班级用户信息
  @GetMapping(value = "/classDetail", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getClassDetail(@RequestParam("id") Integer id) {
    List<Users> classDetail = teacherService.getClassByUserId(id);
    HttpHeaders httpHeaders = new HttpHeaders();
    JSONArray jsonArray = new JSONArray(classDetail);
    String result = jsonArray.toJSONString();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //查看班级邀请码信息
  @GetMapping(value = "/classInviteCode", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> getClassInviteCode(@RequestParam("id") Integer id) {
    List<ClassRoom> classRoomList = teacherService.findClassById(id);
    HttpHeaders httpHeaders = new HttpHeaders();
    JSONArray jsonArray = new JSONArray(classRoomList);
    String result = jsonArray.toJSONString();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //根据自己的ID查看已创建的班级
  @GetMapping(value = "/findAllClassByTeacherId", produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> findAllClassByTeacherId(@RequestParam("teacherId") Integer teacherId) {
    List<ClassRoom> classRoomList = teacherService.findClassByTeacherId(teacherId);
    HttpHeaders httpHeaders = new HttpHeaders();
    JSONArray jsonArray = new JSONArray(classRoomList);
    java.lang.String result = jsonArray.toJSONString();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
  }

  //删除签到
  @GetMapping(value = "/deleteSignIn", produces = "text/plain;charset=UTF-8")
  public String deleteSignIn(@RequestParam("id") Integer id) {
    JSONObject jsonObject = new JSONObject();
    teacherService.deleteSignIn(id);
    jsonObject.put("message", "删除成功");
    jsonObject.put("status", SUCCESS_CODE);
    return jsonObject.toJSONString();
  }
}