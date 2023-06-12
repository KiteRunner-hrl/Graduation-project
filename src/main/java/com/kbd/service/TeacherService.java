package com.kbd.service;

import com.kbd.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
  void createClass(String className, Integer teacherId, String teacherName);
  void createSignIn(Integer teacherId, Integer classId, String lessonName, Integer time);
  ClassRoom findClass(@Param("className") String className);
  //查询所有班级
  List<ClassRoom> findAllClass();
  //根据ID删除班级
  void deleteClass(Integer id);

  int deleteLeave(Integer id);

  int deleteSignIn(Integer id);

  int approveLeavePass(Integer id, Integer status);

  int updateStatusReject(Integer id, Integer status, String reject);

  int updateSignInStatus(Integer id, String status);

  List<Leave> getLeaveById(Integer id);

  List<Leave> getAllLeave();

  List<ClassRoom> findClassByTeacherId(Integer teacherId);

  List<Users> getClassByUserId(Integer classId);

  List<ClassRoom> findClassById(Integer id);

  List<SignIn> getSignInById(Integer id);

  List<Leave> findStudent(String approveTeacher);

  List<StudentSignIn> findAllSignInStudent(Integer sid);

  List<Leave> findStudentByTeacherName(String approveTeacher);
}
