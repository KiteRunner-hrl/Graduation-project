package com.kbd.service.impl;

import com.kbd.mapper.TeacherMapper;
import com.kbd.pojo.*;
import com.kbd.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class TeacherServiceImpl implements TeacherService {
  @Resource
  TeacherMapper teacherMapper;

  //生成8位数字班级邀请码
  private static String generateRandomCode() {
    String chars = "1234567890";
    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(chars.length());
      stringBuilder.append(chars.charAt(index));
    }
    return stringBuilder.toString();
  }

  @Override
  public void createClass(String className, Integer teacherId, String teacherName) {
    String invitationCode = generateRandomCode();
    ClassRoom classRoom = new ClassRoom(0, teacherId, teacherName, className,0, invitationCode);
    if (teacherMapper.addClass(classRoom) <= 0) {
      throw new RuntimeException("班级信息添加失败");
    }
  }

  //教师创建签到活动
  @Override
  public void createSignIn(Integer teacherId, Integer classId, String lessonName, Integer time) {
    SignIn signIn = new SignIn(0, teacherId, classId, lessonName, 0, "正在进行", time);
    teacherMapper.addSignIn(signIn);
  }

  @Override
  public ClassRoom findClass(String className) {
    return teacherMapper.findClass(className);
  }

  //查询所有班级
  @Override
  public List<ClassRoom> findAllClass() {
    return teacherMapper.findAllClass();
  }

  @Override
  public void deleteClass(Integer id) {
    teacherMapper.deleteClass(id);
  }

  @Override
  public int deleteLeave(Integer id) {
    return teacherMapper.deleteLeave(id);
  }

  @Override
  public int deleteSignIn(Integer id) {
    return teacherMapper.deleteSignIn(id);
  }

  //审批请假
  @Override
  public int approveLeavePass(Integer id, Integer status) {
    return teacherMapper.approveLeavePass(id, status);
  }

  @Override
  public int updateStatusReject(Integer id, Integer status, String reject) {
    return teacherMapper.updateStatusReject(id, status, reject);
  }

  //更新签到状态
  @Override
  public int updateSignInStatus(Integer id, String status) {
    return teacherMapper.updateSignInStatus(id, status);
  }

  //根据ID查找请假审批
  @Override
  public List<Leave> getLeaveById(Integer id) {
    return teacherMapper.getLeaveById(id);
  }

  //查看所有的请假审批
  @Override
  public List<Leave> getAllLeave() {
    return teacherMapper.getAllLeave();
  }

  @Override
  public List<ClassRoom> findClassByTeacherId(Integer teacherId) {
    return teacherMapper.findClassByTeacherId(teacherId);
  }

  @Override
  public List<Users> getClassByUserId(Integer classId) {
    return teacherMapper.getClassByUserId(classId);
  }

  @Override
  public List<ClassRoom> findClassById(Integer id) {
    return teacherMapper.findClassById(id);
  }

  @Override
  public List<SignIn> getSignInById(Integer id) {
    return teacherMapper.getSignInById(id);
  }

  @Override
  public List<Leave> findStudent(String approveTeacher) {
    return teacherMapper.findStudent(approveTeacher);
  }

  @Override
  public List<StudentSignIn> findAllSignInStudent(Integer sid) {
    return teacherMapper.findAllSignInStudent(sid);
  }

  @Override
  public List<Leave> findStudentByTeacherName(String approveTeacher) {
    return teacherMapper.findStudentByTeacherName(approveTeacher);
  }
}
