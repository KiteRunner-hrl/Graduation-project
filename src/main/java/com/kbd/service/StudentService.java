package com.kbd.service;

import com.kbd.pojo.ClassRoom;
import com.kbd.pojo.Leave;

import com.kbd.pojo.SignIn;
import com.kbd.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

  void addLeave(Integer userId, String fullName, String lessonName, String leaveType, String leaveReason, String approveTeacher);

  void addStudentSignIn(Integer classId, Integer sid, Integer userId, String username, String finish);

  List<Leave> getLeaveById(Integer id);

  List<SignIn> findSignInListByClassId(Integer classId);

  List<ClassRoom> findClassById(Integer id);

  int checkClassCode(String classCode);

  int update(Users users);

  int updateClassNum(Integer id);

  int updateSignInCount(Integer sid);
}
