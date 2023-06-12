package com.kbd.service.impl;

import com.kbd.mapper.StudentMapper;
import com.kbd.pojo.*;
import com.kbd.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  @Resource
  StudentMapper studentMapper;

  //创建请假申请
  @Override
  public void addLeave(Integer userId, String fullName, String lessonName, String leaveType, String leaveReason, String approveTeacher) {
    Leave leave = new Leave(0, userId, fullName, lessonName, leaveType, leaveReason, approveTeacher, 0, "null");
    studentMapper.insertLeave(leave);
  }

  //学生签到
  @Override
  public void addStudentSignIn(Integer classId, Integer sid, Integer userId, String userName, String finish) {
    StudentSignIn studentSignIn = new StudentSignIn(0, classId, sid, userId, userName, finish);
    studentMapper.addStudentSignIn(studentSignIn);
  }

  //根据ID查询自己的请假申请
  @Override
  public List<Leave> getLeaveById(Integer id) {
    return studentMapper.getLeaveById(id);
  }

  @Override
  public List<SignIn> findSignInListByClassId(Integer classId) {
    return studentMapper.findSignInListByClassId(classId);
  }

  @Override
  public List<ClassRoom> findClassById(Integer id) {
    return studentMapper.findClassById(id);
  }

  //查找班级邀请码
  @Override
  public int checkClassCode(String classCode) {
    return studentMapper.checkClassCode(classCode);
  }

  //更新用户表信息
  @Override
  public int update(Users users) {
    return studentMapper.update(users);
  }

  @Override
  public int updateClassNum(Integer sid) {
    return studentMapper.updateClassNum(sid);
  }

  //更新签到人数
  @Override
  public int updateSignInCount(Integer id) {
    return studentMapper.updateSignInCount(id);
  }
}
