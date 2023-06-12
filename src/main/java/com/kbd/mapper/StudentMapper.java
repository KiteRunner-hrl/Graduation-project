package com.kbd.mapper;

import com.kbd.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

  //创建请假申请
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into leaves(userId, userName, lessonName, leaveType, leaveReason, approveTeacher, status, reject) values(#{userId}, #{userName}, #{lessonName}, #{leaveType}, #{leaveReason}, #{approveTeacher}, #{status}, #{reject})")
  int insertLeave(Leave leave);

  //查看请假申请
  @Select("select * from leaves where userId = #{userId}")
  List<Leave> getLeaveById(@Param("userId") Integer id);

  //查询班级邀请码
  @Select("select * from class where classCode = #{classCode}")
  int checkClassCode(@Param("classCode") String classCode);

  //更新classId字段的值
  @Update("update users set classId = #{classId} where id = #{id}")
  int update(Users users);

  //加入班级后人数+1
  @Update("update class set classNum = classNum + 1 where id = #{id}")
  int updateClassNum(@Param("id") Integer id);

  //根据班级班级ID查询班级信息
  @Select("select * from class where id = #{id}")
  List<ClassRoom> findClassById(@Param("id") Integer id);

  //根据班级ID查看老师创建的签到
  @Select("select * from signin where classId = #{classId}")
  List<SignIn> findSignInListByClassId(@Param("classId") Integer classId);

  //学生签到
  @Insert("insert into studentsignin(classId, sid, userId, username, finish) values(#{classId}, #{sid}, #{userId}, #{username}, #{finish})")
  int addStudentSignIn(StudentSignIn studentSignIn);

  //更新签到状态
  @Update("update signin set signInCount = signInCount + 1 where id = #{sid}")
  int updateSignInCount(@Param("sid") Integer sid);
}
