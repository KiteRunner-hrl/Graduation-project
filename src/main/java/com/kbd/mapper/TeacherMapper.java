package com.kbd.mapper;

import com.kbd.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
  //创建班级
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into class(teacherId, teacherName, className, classNum, classCode) values(#{teacherId}, #{teacherName}, #{className}, #{classNum}, #{classCode})")
  int addClass(ClassRoom classRoom);

  //根据班级名称查询班级
  @Select("select * from class where className = #{className} limit 1")
  ClassRoom findClass(String className);

  //根据班级ID查询班级
  @Select("select * from class where id = #{id}")
  List<ClassRoom> findClassById(@Param("id") Integer id);

  //查看已创建的班级
  @Select("select * from class")
  List<ClassRoom> findAllClass();

  //根据创建班级者的ID查询已创建的班级
  @Select("select * from class where teacherId = #{teacherId}")
  List<ClassRoom> findClassByTeacherId(@Param("teacherId") Integer teacherId);

  //根据班级ID查看加入班级的学生
  @Select("select * from users where classId = #{classId}")
  List<Users> getClassByUserId(@Param("classId") Integer classId);

  //解散班级
  @Delete("delete from class where id = #{id}")
  void deleteClass(Integer id);

  /**
   * 教师请假审批部分
   */

  //查看请假申请
  @Select("select * from leaves where userId = #{userId}")
  List<Leave> getLeaveById(@Param("userId") Integer id);

  //根据教师名称查看请假申请
  @Select("select * from leaves where approveTeacher = #{approveTeacher}")
  List<Leave> findStudentByTeacherName(@Param("approveTeacher") String approveTeacher);

  //查看全部请假申请
  @Select("select * from leaves")
  List<Leave> getAllLeave();

  //审批请假(同意)
  @Update("update leaves set status = #{status} where id = #{id}")
  int approveLeavePass(@Param("id") Integer id, @Param("status") Integer status);

  //审批请假(不同意)
  @Update("update leaves set status = #{status}, reject = #{reject} where id = #{id}")
  int updateStatusReject(@Param("id") Integer id, @Param("status") Integer status, @Param("reject") String reject);

  //审批结束
  @Delete("delete from leaves where id =# {id}")
  int deleteLeave(@Param("id") Integer id);

  //创建签到活动
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into signin(teacherId, classId, lessonName, signInCount, status, time) values(#{teacherId}, #{classId}, #{lessonName}, #{signInCount}, #{status}, #{time})")
  int addSignIn(SignIn signIn);

  //获取当前签到活动
  @Select("select * from signin where id = #{id}")
  List<SignIn> getSignInById(@Param("id") Integer id);

  //更改当前签到活动状态
  @Update("update signin set status = #{status} where id = #{id}")
  int updateSignInStatus(@Param("id") Integer id, @Param("status") String status);

  //删除签到活动
  @Delete("delete from signin where id = #{id}")
  int deleteSignIn(@Param("id") Integer id);

  //根据ID查询已签到的学生
  @Select("select * from studentsignin where sid = #{sid}")
  List<StudentSignIn> findAllSignInStudent(@Param("sid") Integer sid);

  //根据教师名字查询已经请假并通过审批的学生
  @Select("select * from leaves where approveTeacher = #{approveTeacher} and status = 1")
  List<Leave> findStudent(@Param("approveTeacher") String approveTeacher);
}
