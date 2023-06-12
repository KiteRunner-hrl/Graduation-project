package com.kbd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignIn {
  private int id;
  private int teacherId;
  private int classId;
  private String lessonName;
  private int signInCount;
  private String status;
  private int time;
}
